package com.sumo.phoneValidation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumo.phoneValidation.model.customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {  SpringRunner.class })
class PhoneValidationApplicationTests {


	@LocalServerPort
	int randomServerPort;

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


	@Test
	public void testAddCustomerSuccess() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+randomServerPort+"/customers/";
		URI uri = new URI(baseUrl);
		customer Customer = new customer();
		Customer.setName("ALI Mohamed");
		Customer.setPhone("(251) 9119454961");
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		HttpEntity<customer> request = new HttpEntity<>(Customer, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		//Verify request succeed
		Assertions.assertEquals(201, result.getStatusCodeValue());
	}

	@Test
	public void testGetCustomerList() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/customers/";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-LOCATION", "true");

		HttpEntity<customer> requestEntity = new HttpEntity<>(null, headers);
		try
		{
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
			Assertions.assertEquals(200, result.getStatusCodeValue());
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assertions.assertEquals(400, ex.getRawStatusCode());
			Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}
	@Test
	public void testDeleteCustomer() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/customers/41";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-LOCATION", "true");

		HttpEntity<customer> requestEntity = new HttpEntity<>(null, headers);
		try
		{
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, requestEntity, String.class);
			Assertions.assertEquals(204, result.getStatusCodeValue());
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assertions.assertEquals(400, ex.getRawStatusCode());
			Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}

	@Test
	public void testGetCustomerListFilterByCountry() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/customers/search/findByCountry?country=Morocco";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-LOCATION", "true");

		HttpEntity<customer> requestEntity = new HttpEntity<>(null, headers);
		try
		{
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
			Assertions.assertEquals(200, result.getStatusCodeValue());
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assertions.assertEquals(400, ex.getRawStatusCode());
			Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}


	@Test
	public void testGetCustomerListFilterByStateValid() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/customers/search/findByState?State=Valid";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-LOCATION", "true");

		HttpEntity<customer> requestEntity = new HttpEntity<>(null, headers);
		try
		{
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
			Assertions.assertEquals(200, result.getStatusCodeValue());
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assertions.assertEquals(400, ex.getRawStatusCode());
			Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}


	@Test
	public void testGetCustomerListFilterByStateInValid() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/customers/search/findByState?State=InValid";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-LOCATION", "true");

		HttpEntity<customer> requestEntity = new HttpEntity<>(null, headers);
		try
		{
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
			Assertions.assertEquals(200, result.getStatusCodeValue());
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assertions.assertEquals(400, ex.getRawStatusCode());
			Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}

	@Test
	public void testGetCustomerListFilterByCode() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/customers/search/findByCode?Code=212";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-LOCATION", "true");

		HttpEntity<customer> requestEntity = new HttpEntity<>(null, headers);
		try
		{
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
			Assertions.assertEquals(200, result.getStatusCodeValue());
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assertions.assertEquals(400, ex.getRawStatusCode());
			Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}

}
