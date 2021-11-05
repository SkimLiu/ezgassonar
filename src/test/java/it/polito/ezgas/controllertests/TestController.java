package it.polito.ezgas.controllertests;

import java.io.IOException;

import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.PriceReportDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.utils.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.GasStationDto;

import static org.junit.Assert.fail;

@Transactional
public class TestController {
	private static final String LOCALHOST_8080 = "http://localhost:8080";

	private GasStationDto testGasStation;
	private UserDto testUser;

	@Before
	public void setUp() {
		// insert a GasStationDto in the DB
		final GasStationDto gasStation = new GasStationDto();
		gasStation.setGasStationName("test station");
		gasStation.setGasStationAddress("via taggia 64");
		gasStation.setLat(0.0);
		gasStation.setLon(0.0);
		gasStation.setCarSharing("enjoy");
		gasStation.setHasDiesel(true);
		gasStation.setHasGas(false);
		gasStation.setHasMethane(true);
		gasStation.setHasSuper(true);
		gasStation.setHasSuperPlus(false);

		testGasStation = saveGasStation(gasStation);

		// insert a UserDto in the DB
		final UserDto user = new UserDto();
		user.setUserName("test user");
		user.setEmail("test@mail.com");
		user.setPassword("pwd");
		user.setAdmin(false);
		user.setReputation(0);

		testUser = saveUser(user);
	}

	@After
	public final void tearDown() {
		deleteGasStation(testGasStation.getGasStationId());
		deleteUser(testUser.getUserId());
	}

	@Test
	public void testGetUserById() {
		String uri = LOCALHOST_8080 + Constants.USER + Constants.GET_USER_BY_ID;
		uri = uri.replace(Constants.USER_ID, Integer.toString(testUser.getUserId()));

		HttpUriRequest request = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			UserDto user = mapper.readValue(jsonFromResponse, UserDto.class);

			assert (user.getUserId().equals(testUser.getUserId()));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetAllUsers() {
		String uri = LOCALHOST_8080 + Constants.USER + Constants.GET_ALL_USERS;

		HttpUriRequest request = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			UserDto[] userArray = mapper.readValue(jsonFromResponse, UserDto[].class);

			assert (userArray.length > 0);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSaveUser() {
		UserDto user = new UserDto();
		user.setUserName("second");
		user.setEmail("second@mail.com");
		user.setPassword("pwd");

		user  = saveUser(user);

		deleteUser(user.getUserId());
	}

	@Test
	public void testDeleteUser() {
		deleteUser(testUser.getUserId());
	}

	@Test
	public void testIncreaseReputation() {
		String uri = LOCALHOST_8080 + Constants.USER + Constants.INCREASE_REPUTATION;
		uri = uri.replace(Constants.USER_ID, Integer.toString(testUser.getUserId()));

		HttpUriRequest request = new HttpPost(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);

			assert (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDecreaseReputation() {
		String uri = LOCALHOST_8080 + Constants.USER + Constants.DECREASE_REPUTATION;
		uri = uri.replace(Constants.USER_ID, Integer.toString(testUser.getUserId()));

		HttpUriRequest request = new HttpPost(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);

			assert (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testLogin() {
		IdPw credentials = new IdPw();
		credentials.setUser(testUser.getEmail());
		credentials.setPw(testUser.getPassword());

		String uri = LOCALHOST_8080 + Constants.USER + Constants.LOGIN;

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			StringEntity params = new StringEntity(mapper.writeValueAsString(credentials));
			HttpPost request = new HttpPost(uri);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.setEntity(params);
			HttpResponse response = HttpClientBuilder.create().build().execute(request);

			assert (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetGasStationById() {
		String uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.GET_GASSTATION_BY_ID;
		uri = uri.replace(Constants.GAS_STATION_ID, Integer.toString(testGasStation.getGasStationId()));

		HttpUriRequest request = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			GasStationDto gasStation = mapper.readValue(jsonFromResponse, GasStationDto.class);

			assert (gasStation.getGasStationId().equals(testGasStation.getGasStationId()));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetAllGasStations() {
		final String uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.GET_ALL_GASSTATIONS;

		HttpUriRequest request = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			GasStationDto[] gasStationArray = mapper.readValue(jsonFromResponse, GasStationDto[].class);

			assert (gasStationArray.length > 0);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSaveGasStation() {
		GasStationDto gasStation = new GasStationDto();
		gasStation.setGasStationName("second");
		gasStation.setGasStationAddress("via taggia 64");
		gasStation.setLat(0.0);
		gasStation.setLon(0.0);
		gasStation.setCarSharing("enjoy");
		gasStation.setHasDiesel(true);
		gasStation.setHasGas(false);
		gasStation.setHasMethane(true);
		gasStation.setHasSuper(true);
		gasStation.setHasSuperPlus(false);

		gasStation = saveGasStation(gasStation);

		deleteGasStation(gasStation.getGasStationId());
	}

	@Test
	public void testDeleteGasStation() {
		deleteGasStation(testGasStation.getGasStationId());
	}

	@Test
	public void testGetGasStationsByGasolineType() {
		final String gasolineType = "Super";

		String uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.GET_GASSTATIONS_BY_GASOLINETYPE;
		uri = uri.replace(Constants.GASOLINE_TYPE_NO_CAMEL, gasolineType);

		HttpUriRequest request = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			GasStationDto[] gasStationArray = mapper.readValue(jsonFromResponse, GasStationDto[].class);

			for (GasStationDto g: gasStationArray)
				assert (g.getHasSuper());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetGasStationsByProximity() {
		final double lat = 0.0;
		final double lon = 0.0;
		final int myRadius = 1;

		String uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.GET_GASSTATIONS_BY_PROXIMITY;
		uri = uri.replace(Constants.LAT, Double.toString(lat));
		uri = uri.replace(Constants.LON, Double.toString(lon));
		uri = uri.replace(Constants.RADIUS, Integer.toString(myRadius));

		HttpUriRequest request = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			GasStationDto[] gasStationArray = mapper.readValue(jsonFromResponse, GasStationDto[].class);

			assert (gasStationArray.length > 0);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSetGasStationsReport() {
		PriceReportDto priceReportDto = new PriceReportDto(testGasStation.getGasStationId(), 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, testUser.getUserId());
		
		String uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.SET_GASSTATION_REPORT;
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			StringEntity params = new StringEntity(mapper.writeValueAsString(priceReportDto));
			HttpPost post = new HttpPost(uri);
			post.addHeader("Content-Type", "application/json");
			post.addHeader("Accept", "application/json");
			post.setEntity(params);
			HttpResponse response = HttpClientBuilder.create().build().execute(post);

			assert (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testGetGasStationsWithCoordinates() {
		final double lat = 0.0;
		final double lon = 0.0;
		final String gasolineType = "super";
		final String carSharing = "enjoy";
		final int myRadius = 1;

		String uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.GET_GASSTATIONS_WITH_COORDINATES;
		uri = uri.replace(Constants.LAT, Double.toString(lat));
		uri = uri.replace(Constants.LON, Double.toString(lon));
		uri = uri.replace(Constants.RADIUS, Integer.toString(myRadius));
		uri = uri.replace(Constants.GASOLINE_TYPE, gasolineType);
		uri = uri.replace(Constants.CAR_SHARING, carSharing);

		HttpUriRequest request = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			GasStationDto[] gasStationArray = mapper.readValue(jsonFromResponse, GasStationDto[].class);

			for (GasStationDto g: gasStationArray)
				assert (g.getHasSuper() && g.getCarSharing().equals(carSharing));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	private UserDto saveUser(UserDto user) {
		String uri = LOCALHOST_8080 + Constants.USER + Constants.SAVE_USER;

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			StringEntity params = new StringEntity(mapper.writeValueAsString(user));
			HttpPost post = new HttpPost(uri);
			post.addHeader("Content-Type", "application/json");
			post.addHeader("Accept", "application/json");
			post.setEntity(params);
			HttpResponse response = HttpClientBuilder.create().build().execute(post);

			assert (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		} catch (IOException e) {
			fail(e.getMessage());
		}

		// get the inserted UserDto back
		uri = LOCALHOST_8080 + Constants.USER + Constants.GET_ALL_USERS;

		HttpGet get = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(get);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			UserDto[] userArray = mapper.readValue(jsonFromResponse, UserDto[].class);

			return userArray[userArray.length - 1];
		} catch (IOException e) {
			fail(e.getMessage());
		}

		return null;
	}

	private void deleteUser(int userId) {
		String uri = LOCALHOST_8080 + Constants.USER + Constants.DELETE_USER;

		uri = uri.replace(Constants.USER_ID, Integer.toString(userId));

		HttpUriRequest request = new HttpDelete(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);

			assert (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	private GasStationDto saveGasStation(GasStationDto gasStation) {
		String uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.SAVE_GASSTATION;

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			StringEntity params = new StringEntity(mapper.writeValueAsString(gasStation));
			HttpPost post = new HttpPost(uri);
			post.addHeader("Content-Type", "application/json");
			post.addHeader("Accept", "application/json");
			post.setEntity(params);
			HttpResponse response = HttpClientBuilder.create().build().execute(post);

			assert (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		} catch (IOException e) {
			fail(e.getMessage());
		}

		// get the inserted GasStationDto back, in order to know its id
		uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.GET_ALL_GASSTATIONS;

		HttpGet get = new HttpGet(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(get);
			String jsonFromResponse = EntityUtils.toString(response.getEntity());

			mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			GasStationDto[] gasStationArray = mapper.readValue(jsonFromResponse, GasStationDto[].class);

			return gasStationArray[gasStationArray.length - 1];
		} catch (IOException e) {
			fail(e.getMessage());
		}

		return null;
	}

	private void deleteGasStation(int gasStationId) {
		String uri = LOCALHOST_8080 + Constants.GAS_STATION + Constants.DELETE_GASSTATION;
		uri = uri.replace(Constants.GAS_STATION_ID, Integer.toString(gasStationId));

		HttpUriRequest request = new HttpDelete(uri);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);

			assert (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
