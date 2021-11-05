package it.polito.ezgas.utils;

public interface Constants {
	static final String GET_USER_BY_ID = "/getUser/{userId}";
	static final String GET_ALL_USERS = "/getAllUsers";
	static final String SAVE_USER = "/saveUser";
	static final String DELETE_USER = "/deleteUser/{userId}";
	static final String INCREASE_REPUTATION = "/increaseUserReputation/{userId}";
	static final String DECREASE_REPUTATION = "/decreaseUserReputation/{userId}";
	static final String LOGIN = "/login";
	static final String GET_GASSTATION_BY_ID = "/getGasStation/{gasStationId}";
	static final String GET_ALL_GASSTATIONS = "/getAllGasStations";
	static final String SAVE_GASSTATION = "/saveGasStation";
	static final String DELETE_GASSTATION = "/deleteGasStation/{gasStationId}";
	static final String GET_GASSTATIONS_BY_NEIGHBORHOOD = "/searchGasStationByNeighborhood/{neighborhood}";
	static final String GET_GASSTATIONS_BY_GASOLINETYPE = "/searchGasStationByGasolineType/{gasolinetype}";
	//static final String GET_GASSTATIONS_BY_PROXIMITY = "/searchGasStationByProximity/{myLat}/{myLon}";
	static final String GET_GASSTATIONS_BY_PROXIMITY = "/searchGasStationByProximity/{myLat}/{myLon}/{myRadius}";
	//static final String SET_GASSTATION_REPORT = "/setGasStationReport/{gasStationId}/{dieselPrice}/{superPrice}/{superPlusPrice}/{gasPrice}/{methanePrice}/{userId}";
	//static final String SET_GASSTATION_REPORT = "/setGasStationReport/{gasStationId}/{dieselPrice}/{superPrice}/{superPlusPrice}/{gasPrice}/{methanePrice}/{premiumDieselPrice}/{userId}";
	static final String SET_GASSTATION_REPORT = "/setGasStationReport";

	//static final String GET_GASSTATIONS_WITH_COORDINATES = "/getGasStationsWithCoordinates/{myLat}/{myLon}/{gasolineType}/{carSharing}";
	static final String GET_GASSTATIONS_WITH_COORDINATES = "/getGasStationsWithCoordinates/{myLat}/{myLon}/{myRadius}/{gasolineType}/{carSharing}";
	static final String GET_GASSTATIONS_WITHOUT_COORDINATES = "/getGasStationsWithoutCoordinates/{gasolineType}/{carSharing}";
	String GAS_STATION = "/gasstation";
	String USER = "/user";
	String USER_ID = "{userId}";
	String RADIUS = "{myRadius}";
	String GAS_STATION_ID = "{gasStationId}";
	String NEIGHBORHOOD = "{neighborhood}";
	String GASOLINE_TYPE_NO_CAMEL = "{gasolinetype}";	// this is needed for GET_GASSTATIONS_BY_GASOLINETYPE
	String GASOLINE_TYPE = "{gasolineType}";
	String LAT = "{myLat}";
	String LON = "{myLon}";
	String DIESEL_PRICE = "{dieselPrice}";
	String SUPER_PRICE = "{superPrice}";
	String SUPER_PLUS_PRICE = "{superPlusPrice}";
	String GAS_PRICE = "{gasPrice}";
	String METHANE_PRICE = "{methanePrice}";
	String CAR_SHARING = "{carSharing}";
}
