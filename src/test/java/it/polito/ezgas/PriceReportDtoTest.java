package it.polito.ezgas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.PriceReportDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceReportDtoTest {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testPriceReportDtoGetterSetter() {
		
		PriceReportDto priceReportDto = new PriceReportDto();
		
		priceReportDto.setGasStationId(1);
		priceReportDto.setDieselPrice(2.0);
		priceReportDto.setGasPrice(1.0);
		priceReportDto.setMethanePrice(1.0);
		priceReportDto.setPremiumDieselPrice(2.0);
		priceReportDto.setSuperPlusPrice(1.0);
		priceReportDto.setSuperPrice(2.0);
		priceReportDto.setUserId(1);
		
		assert(priceReportDto.getGasStationId() == 1);
		assert(priceReportDto.getDieselPrice() == 2.0);
		assert(priceReportDto.getGasPrice() == 1.0);
		assert(priceReportDto.getMethanePrice() == 1.0);
		assert(priceReportDto.getPremiumDieselPrice() == 2.0);
		assert(priceReportDto.getSuperPlusPrice() == 1.0);
		assert(priceReportDto.getSuperPrice() == 2.0);
		assert(priceReportDto.getUserId() == 1);
		
	}
	
	@Test
	public void testPriceReportDtoConstructor() {
		
		PriceReportDto priceReportDto = new PriceReportDto(1, new Double(2.0), new Double(2.0), new Double(1.0),  new Double(1.0),
																new Double(1.0), new Double(2.0), 1);
		
		assert(priceReportDto.getGasStationId() == 1);
		assert(priceReportDto.getDieselPrice() == 2.0);
		assert(priceReportDto.getGasPrice() == 1.0);
		assert(priceReportDto.getMethanePrice() == 1.0);
		assert(priceReportDto.getPremiumDieselPrice() == 2.0);
		assert(priceReportDto.getSuperPlusPrice() == 1.0);
		assert(priceReportDto.getSuperPrice() == 2.0);
		assert(priceReportDto.getUserId() == 1);
		
	}
	
}
