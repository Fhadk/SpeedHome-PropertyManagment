package com.SpeedHome;

import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpeedHomeApplicationTests {

	@LocalServerPort
	int randomServerPort;
	
	private HttpHeaders requestHeaders = new HttpHeaders();
	private HttpEntity<String> request;
	private ResponseEntity<String> response;
	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void createProperty() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/createProperty/commerical/USA-123";

		requestHeaders.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJla2huZmhkIiwiZXhwIjoxNjE4MDEyMzQ4LCJpYXQiOjE2MTc5NzYzNDh9.6xziqpERZcx66PjYke-9HfHx8Z2-ONeoJ_VM2cU6pVo");
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		request = new HttpEntity<String>(requestHeaders);
		response = restTemplate.exchange(baseUrl, HttpMethod.GET, request, String.class);
		
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals(true, response.getBody().contains("USA"));
	}
	
	@Test
	public void aprroveProperty() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/aprroveProperty/USA-123";
		
		requestHeaders.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJla2huZmhkIiwiZXhwIjoxNjE4MDEyMzQ4LCJpYXQiOjE2MTc5NzYzNDh9.6xziqpERZcx66PjYke-9HfHx8Z2-ONeoJ_VM2cU6pVo");
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		request = new HttpEntity<String>(requestHeaders);
		response = restTemplate.exchange(baseUrl, HttpMethod.GET, request, String.class);
		
		System.out.println(response.getBody().toString());

		Assert.assertEquals(200, response.getStatusCodeValue());
	}
	
	
	@Test
	public void getActiveUser() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/getActiveUser";
		
		requestHeaders.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJla2huZmhkIiwiZXhwIjoxNjE4MDEyMzQ4LCJpYXQiOjE2MTc5NzYzNDh9.6xziqpERZcx66PjYke-9HfHx8Z2-ONeoJ_VM2cU6pVo");
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		request = new HttpEntity<String>(requestHeaders);
		response = restTemplate.exchange(baseUrl, HttpMethod.GET, request, String.class);

		Assert.assertEquals(200, response.getStatusCodeValue());
	}
}

