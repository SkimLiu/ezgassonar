package it.polito.ezgas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasStationConverterTest {

	//this test is needed only to reach full coverage computed by Eclemma
	@Test
	public void coverage() {
		GasStationConverter gConverter = new GasStationConverter();
	}
	
	@Test
	public void testGasStationConverterToGasStationDto() {
		
		GasStation g = new GasStation("name", "address", true, true, false, false, true,true, 
							"carSharing", 40, 50, new Double(1.5), new Double(1.6), 
							new Double(1.7), new Double(1.8), new Double(1.9),new Double(2.0), 2, "timestamp", 0);
		
		User u = new User();
		u.setUserId(1);
		
		g.setGasStationId(1);
		g.setUser(u);
		
		GasStationDto gDto = GasStationConverter.toGasStationDto(g);
		
		assertEquals(gDto.getGasStationName(),g.getGasStationName());
		assertEquals(gDto.getGasStationAddress(),g.getGasStationAddress());
		assertEquals(gDto.getHasDiesel(),g.getHasDiesel());
		assertEquals(gDto.getHasGas(),g.getHasGas());
		assertEquals(gDto.getHasMethane(),g.getHasMethane());
		assertEquals(gDto.getHasSuper(),g.getHasSuper());
		assertEquals(gDto.getHasSuperPlus(),g.getHasSuperPlus());
		assertEquals(gDto.getCarSharing(),g.getCarSharing());
		assertEquals(gDto.getHasPremiumDiesel(), g.getHasPremiumDiesel());
		assert(gDto.getLat() == g.getLat());
		assert(gDto.getLon() == g.getLon());
		assert(gDto.getDieselPrice() == g.getDieselPrice());
		assert(gDto.getGasPrice() == g.getGasPrice());
		assert(gDto.getMethanePrice() == g.getMethanePrice());
		assert(gDto.getSuperPrice() == g.getSuperPrice());
		assert(gDto.getSuperPlusPrice() == g.getSuperPlusPrice());
		assert(gDto.getPremiumDieselPrice() == g.getPremiumDieselPrice());
		assertEquals(gDto.getReportUser(),g.getReportUser());
		assertEquals(gDto.getReportTimestamp(),g.getReportTimestamp());
		assert(gDto.getReportDependability() == g.getReportDependability());
		assertEquals(gDto.getGasStationId(), g.getGasStationId());
		assert(gDto.getUserDto().getUserId() == g.getUser().getUserId());
		
	}
			
	@Test
	public void testGasStationConverterToGasStation() {
		
		GasStationDto gDto = new GasStationDto(1,"name", "address", true, true, false, false, true, true,
				"carSharing", 40, 50, new Double(1.5), new Double(1.6), new Double(1.7), new Double(1.8),
				new Double(1.9), new Double(2.0), 2, "timestamp", 0);

		UserDto uDto = new UserDto();
		uDto.setUserId(1);
		
		gDto.setUserDto(uDto);
		
		GasStation g = GasStationConverter.toGasStation(gDto);
		
		assertEquals(gDto.getGasStationName(),g.getGasStationName());
		assertEquals(gDto.getGasStationAddress(),g.getGasStationAddress());
		assertEquals(gDto.getHasDiesel(),g.getHasDiesel());
		assertEquals(gDto.getHasGas(),g.getHasGas());
		assertEquals(gDto.getHasMethane(),g.getHasMethane());
		assertEquals(gDto.getHasSuper(),g.getHasSuper());
		assertEquals(gDto.getHasSuperPlus(),g.getHasSuperPlus());
		assertEquals(gDto.getHasPremiumDiesel(), g.getHasPremiumDiesel());
		assertEquals(gDto.getCarSharing(),g.getCarSharing());
		assert(gDto.getLat() == g.getLat());
		assert(gDto.getLon() == g.getLon());
		assert(gDto.getDieselPrice() == g.getDieselPrice());
		assert(gDto.getGasPrice() == g.getGasPrice());
		assert(gDto.getMethanePrice() == g.getMethanePrice());
		assert(gDto.getSuperPrice() == g.getSuperPrice());
		assert(gDto.getSuperPlusPrice() == g.getSuperPlusPrice());
		assert(gDto.getPremiumDieselPrice() == g.getPremiumDieselPrice());
		assertEquals(gDto.getReportUser(),g.getReportUser());
		assertEquals(gDto.getReportTimestamp(),g.getReportTimestamp());
		assert(gDto.getReportDependability() == g.getReportDependability());
		assertEquals(gDto.getGasStationId(), g.getGasStationId());
		assert(g.getUser().getUserId() == gDto.getUserDto().getUserId());
		
	}
	
}
