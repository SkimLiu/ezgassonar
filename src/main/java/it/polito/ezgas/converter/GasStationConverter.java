package it.polito.ezgas.converter;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

public class GasStationConverter {
	public static GasStationDto toGasStationDto(GasStation entity) {
		GasStationDto dto = new GasStationDto();
		
		dto.setGasStationId(entity.getGasStationId());
		dto.setGasStationName(entity.getGasStationName());
		dto.setGasStationAddress(entity.getGasStationAddress());
		dto.setHasDiesel(entity.getHasDiesel());
		dto.setHasSuper(entity.getHasSuper());
		dto.setHasSuperPlus(entity.getHasSuperPlus());
		dto.setHasGas(entity.getHasGas());
		dto.setHasMethane(entity.getHasMethane());
		dto.setCarSharing(entity.getCarSharing());
		dto.setLat(entity.getLat());
		dto.setLon(entity.getLon());
		dto.setHasPremiumDiesel(entity.getHasPremiumDiesel());
		dto.setDieselPrice(entity.getDieselPrice());
		dto.setSuperPrice(entity.getSuperPrice());
		dto.setSuperPlusPrice(entity.getSuperPlusPrice());
		dto.setGasPrice(entity.getGasPrice());
		dto.setMethanePrice(entity.getMethanePrice());
		dto.setReportUser(entity.getReportUser());
		dto.setReportTimestamp(entity.getReportTimestamp());
		dto.setReportDependability(entity.getReportDependability());
		dto.setPremiumDieselPrice(entity.getPremiumDieselPrice());
		if(entity.getUser() != null)
			dto.setUserDto(UserConverter.toUserDto(entity.getUser()));
		
		return dto;
	}
		
	public static GasStation toGasStation(GasStationDto dto) {
		GasStation entity = new GasStation();
		
		entity.setGasStationId(dto.getGasStationId());
		entity.setGasStationName(dto.getGasStationName());
		entity.setGasStationAddress(dto.getGasStationAddress());
		entity.setHasDiesel(dto.getHasDiesel());
		entity.setHasSuper(dto.getHasSuper());
		entity.setHasSuperPlus(dto.getHasSuperPlus());
		entity.setHasPremiumDiesel(dto.getHasPremiumDiesel());
		entity.setHasGas(dto.getHasGas());
		entity.setHasMethane(dto.getHasMethane());
		entity.setCarSharing(dto.getCarSharing());
		entity.setLat(dto.getLat());
		entity.setLon(dto.getLon());
		entity.setDieselPrice(dto.getDieselPrice());
		entity.setSuperPrice(dto.getSuperPrice());
		entity.setSuperPlusPrice(dto.getSuperPlusPrice());
		entity.setGasPrice(dto.getGasPrice());
		entity.setMethanePrice(dto.getMethanePrice());
		entity.setReportUser(dto.getReportUser());
		entity.setReportTimestamp(dto.getReportTimestamp());
		entity.setReportDependability(dto.getReportDependability());
		entity.setPremiumDieselPrice(dto.getPremiumDieselPrice());
		if(dto.getUserDto() != null)
			entity.setUser(UserConverter.toUser(dto.getUserDto()));
		
		return entity;	
	}
}
