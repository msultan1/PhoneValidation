package com.sumo.phoneValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneValidationApplication.class, args);

		//countryService cs = Spring.bean(countryService.class);
		//cs.compute();
	}

}
