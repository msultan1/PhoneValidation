package com.sumo.phoneValidation;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@TestConfiguration
public class PhoneValidationApplicationTestConfigurations {
    @Bean
    @Primary
    public countryService CountryService() {
        return Mockito.mock(countryService.class);
    }
}
