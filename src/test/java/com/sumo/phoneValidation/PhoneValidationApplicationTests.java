package com.sumo.phoneValidation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {  SpringRunner.class })
class PhoneValidationApplicationTests {


	private countryService CountryService;

	@Autowired
	public void setCountryService(countryService CountryService){
		this.CountryService = CountryService;
	}

	@Test
	void TestGetCode() {
		String code = CountryService.GetCode("(212) 6007989253");
		Assertions.assertEquals("212",code);
	}

	@Test
	void TestGetState() {
		String code = CountryService.GetState("(212) 6007989253");
		Assertions.assertEquals("InValid",code);
	}

	@Test
	void TestGetCountry() {
		String code = CountryService.GetCountry("(212) 6007989253");
		Assertions.assertEquals("Morocco",code);
	}

}
