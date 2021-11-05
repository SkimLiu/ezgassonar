package it.polito.ezgas.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import exception.*;
import org.springframework.stereotype.Service;

import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService {
	private static final int DEFAULT_PROXIMITY_RADIUS = 1000;
	public static final String[] CAR_SHARING_SERVICES = {
			"Car2Go",
			"Enjoy"
	};
	private GasStationRepository gasStationRepository;
	
	private UserRepository userRepository;
	
	public GasStationServiceimpl(GasStationRepository gasStationRepository, UserRepository userRepository) {
		this.gasStationRepository = gasStationRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		
		if (gasStationId == null)
			throw new InvalidGasStationException("Null gasStationId is not valid");
		
		if (gasStationId < 0)
			throw new InvalidGasStationException("Negative gasStationId is not valid");

		GasStation g = gasStationRepository.findOne(gasStationId);
		
		if (g == null)
			return null;

		g.setReportDependability(dependability(g));

		return GasStationConverter.toGasStationDto(g);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		if (!isCoordinateValid(gasStationDto.getLat(), gasStationDto.getLon()))
			throw new GPSDataException("Invalid GPS data");
		
		if(gasStationDto.getCarSharing() != null && gasStationDto.getCarSharing().equals("null"))
			gasStationDto.setCarSharing(null);

		GasStation g = GasStationConverter.toGasStation(gasStationDto);
		
		if (!isPriceValid(g))
			throw new PriceException("Invalid price detected");
		

		return GasStationConverter.toGasStationDto(gasStationRepository.save(g));
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		return gasStationRepository.findAll()
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		
		if (gasStationId == null)
			throw new InvalidGasStationException("Null gasStationId is not valid");
		
		if (gasStationId < 0)
			throw new InvalidGasStationException("Negative gasStationId is not valid");
		
		GasStation g = gasStationRepository.findOne(gasStationId);
		
		if (g == null)
			return false;
		
		gasStationRepository.delete(g);
		
		return true;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolineType) throws InvalidGasTypeException {
		List<GasStation> gasStations;
		
		if (gasolineType == null)
			throw new InvalidGasTypeException("Null gasoline type is not valid");
		
		switch (gasolineType.toLowerCase()) {
			case "super":
				gasStations = gasStationRepository.findByHasSuperTrueOrderBySuperPrice();
				break;
			case "superplus":
				gasStations = gasStationRepository.findByHasSuperPlusTrueOrderBySuperPlusPrice();
				break;
			case "gas":
				gasStations = gasStationRepository.findByHasGasTrueOrderByGasPrice();
				break;
			case "diesel":
				gasStations = gasStationRepository.findByHasDieselTrueOrderByDieselPrice();
				break;
			case "methane":
				gasStations = gasStationRepository.findByHasMethaneTrueOrderByMethanePrice();
				break;
			case "premiumdiesel":
				gasStations = gasStationRepository.findByHasPremiumDieselTrueOrderByPremiumDieselPrice();
				break;
			default:
				throw new InvalidGasTypeException("No corresponding gas type for gasolineType=" + gasolineType);
		}
		
		return gasStations
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if (!isCoordinateValid(lat, lon))
			throw new GPSDataException("Invalid GPS data");
		
		return gasStationRepository.findAll()
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.filter(s -> distance(s.getLat(), s.getLon(), lat, lon) < DEFAULT_PROXIMITY_RADIUS)
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon, int radius) throws GPSDataException {
		if (!isCoordinateValid(lat, lon))
			throw new GPSDataException("Invalid GPS data");

		final int radius_meters;
		if(radius <= 0)
			radius_meters = DEFAULT_PROXIMITY_RADIUS;
		else
			radius_meters = radius * 1000;	// convert to meters

		return gasStationRepository.findAll()
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.filter(s -> distance(s.getLat(), s.getLon(), lat, lon) < radius_meters)
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, int radius, String gasolinetype, String carsharing) throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException {
		
		if (!isCoordinateValid(lat, lon))
			throw new GPSDataException("Invalid GPS data");

		
		return getGasStationsWithoutCoordinates(gasolinetype, carsharing).stream()
				.filter(s -> distance(s.getLat(), s.getLon(), lat, lon) < DEFAULT_PROXIMITY_RADIUS)
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException, InvalidCarSharingException {
		if (carsharing == null)
			throw new InvalidCarSharingException("null carsharing string is not valid");
		if (gasolinetype == null)
			throw new InvalidGasTypeException("null gasolinetype is not valid");

		if (!carsharing.equals("null")) {
			boolean validCarSharing = false;
			for (String cs : CAR_SHARING_SERVICES) {
				if (carsharing.toLowerCase().equals(cs.toLowerCase())) {
					validCarSharing = true;
					break;
				}
			}

			if (!validCarSharing)
				throw new InvalidCarSharingException("Car sharing " + carsharing + " is not valid");
		}

		List<GasStationDto> gasStations;

		if (!gasolinetype.equals("null")) {
			gasStations = getGasStationsByGasolineType(gasolinetype);
			if (!carsharing.equals("null"))
				gasStations = gasStations.stream()
				.filter(s -> carsharing.equals(s.getCarSharing()))
				.collect(Collectors.toList());
		} else if (!carsharing.equals("null")) {
			gasStations = getGasStationByCarSharing(carsharing);
		} else {
			gasStations = getAllGasStations();
		}
		
		return gasStations.stream()
				.peek(s -> s.setReportDependability(dependability(GasStationConverter.toGasStation(s))))
				.collect(Collectors.toList());
	}

	@Override
	public void setReport(Integer gasStationId, Double dieselPrice, Double superPrice, Double superPlusPrice, Double gasPrice, Double methanePrice, Double premiumDieselPrice, Integer userId) throws InvalidGasStationException, PriceException, InvalidUserException {
		if (gasStationId == null)
			throw new InvalidGasStationException("Null gasStationId is not valid");

		GasStation g = gasStationRepository.findOne(gasStationId);
		if (g == null)
			throw new InvalidGasStationException("No corresponding gas station for gasStationId=" + gasStationId);

		if (userId == null)
			throw new InvalidUserException("Null userId is not valid");

		User newUser = userRepository.findOne(userId);
		if (newUser == null)
			throw new InvalidUserException("No corresponding user for userId=" + userId);
		
		Boolean overwriteReport = true;
		if (g.getReportUser() != null) {
			User oldUser = userRepository.findOne(g.getReportUser());
			if (oldUser != null && newUser.getReputation() < oldUser.getReputation()) {
				String oldTimestamp = g.getReportTimestamp();
				if (oldTimestamp != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
					LocalDate old = LocalDate.parse(oldTimestamp, formatter);
					LocalDate now = LocalDate.now();
					if (ChronoUnit.DAYS.between(old, now) <= 4) 
						overwriteReport = false;
				}
			}
		}
			
		if (overwriteReport) {
			g.setDieselPrice(dieselPrice);
			g.setSuperPrice(superPrice);
			g.setSuperPlusPrice(superPlusPrice);
			g.setGasPrice(gasPrice);
			g.setMethanePrice(methanePrice);
			g.setPremiumDieselPrice(premiumDieselPrice);

			if (!isReportPriceValid(g))
				throw new PriceException("Invalid price detected");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

			g.setReportUser(userId);
			g.setReportTimestamp(formatter.format(LocalDate.now()));
			g.setReportDependability(dependability(g));
			g.setUser(newUser);
		
			gasStationRepository.save(g);
		}
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		return gasStationRepository.findByCarSharingOrderByCarSharing(carSharing)
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	/*
	 * 	Price validity criterion:
	 * 		- if the boolean related to the price is true and the price is not null it must be positive
	 *
	 * 	@return true when all the prices of GasStation g are valid
	 */
	public static boolean isPriceValid(GasStation g) {
				
		Double price = g.getDieselPrice();
		if (g.getHasDiesel() && price != null && price < 0)
				return false;
		else if(g.getHasDiesel() && price == null)
				g.setDieselPrice(0.0); //default price
		else if(!g.getHasDiesel() && price != null)
				g.setDieselPrice(null); //null price if the gasoline type is false
			

		price = g.getPremiumDieselPrice();
		if (g.getHasPremiumDiesel() && price != null && price < 0)
				return false;
		else if(g.getHasPremiumDiesel() && price == null)
				g.setPremiumDieselPrice(0.0);
		else if(!g.getHasPremiumDiesel() && price != null)
				g.setPremiumDieselPrice(null);
			
		
		price = g.getSuperPrice();
		if (g.getHasSuper() && price != null && price < 0)
				return false;
		else if(g.getHasSuper() && price == null)
				g.setSuperPrice(0.0);
		else if(!g.getHasSuper() && price != null)
				g.setSuperPrice(null);

		
		price = g.getSuperPlusPrice();
		if (g.getHasSuperPlus() && price != null && price < 0)
				return false;
		else if(g.getHasSuperPlus() && price == null)
				g.setSuperPlusPrice(0.0);
		else if(!g.getHasSuperPlus() && price != null)
				g.setSuperPlusPrice(null);

		
		price = g.getGasPrice();
		if (g.getHasGas() && price != null && price < 0)
				return false;
		else if(g.getHasGas() && price == null)
				g.setGasPrice(0.0);
		else if(!g.getHasGas() && price != null)
				g.setGasPrice(null);

		
		price = g.getMethanePrice();
		if (g.getHasMethane() && price != null && price < 0)
				return false;
		else if(g.getHasMethane() && price == null)
				g.setMethanePrice(0.0);
		else if(!g.getHasMethane() && price != null)
				g.setMethanePrice(null);
		
		return true;

	}
	
	public static boolean isReportPriceValid(GasStation g) {
		
		Double price = g.getDieselPrice();
		if (g.getHasDiesel() && (price == null || price < 0))
				return false;
			
		price = g.getPremiumDieselPrice();
		if (g.getHasPremiumDiesel() && (price == null || price < 0))
				return false;
		
		price = g.getSuperPrice();
		if (g.getHasSuper() && (price == null || price < 0))
				return false;
		
		price = g.getSuperPlusPrice();
		if (g.getHasSuperPlus() && (price == null || price < 0))
				return false;
		
		price = g.getGasPrice();
		if (g.getHasGas() && (price == null || price < 0))
				return false;
		
		price = g.getMethanePrice();
		if (g.getHasMethane() && (price == null || price < 0))
				return false;
		
		return true;

	}
	
	
	public static boolean isCoordinateValid(double lat, double lon) {
		return lat >= -90 && lat <= 90 && lon >= -180 && lon <= 180;
	}

	public double dependability(GasStation gasStation) {
		String timestamp = gasStation.getReportTimestamp();

		if(timestamp == null)
			return 0;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
		LocalDate old = LocalDate.parse(timestamp, formatter);
		LocalDate now = LocalDate.now();
		
		long ageDays = ChronoUnit.DAYS.between(old, now);
		
		double obsolescence = ageDays < 7 ? 1 - ((double)ageDays / 7) : 0;

		long reputation;
		User u = userRepository.findOne(gasStation.getReportUser());
		
		// if user associated to the report is not found, reputation is equal to the lowest value
		if(u == null)
			reputation = -5;
		else
			reputation = u.getReputation();

		return 5 * (reputation + 5) + 50 * obsolescence;
	}

	/**
	 * Calculate distance between two points in latitude and longitude.
	 * Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point
	 * @return Distance in Meters
	 */
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    
	    return R * c * 1000; // convert to meters
	}
}
