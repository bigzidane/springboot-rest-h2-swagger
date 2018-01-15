package com.es.challenge.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.es.challenge.CalorieTrackingApplication;
import com.es.challenge.domain.Food;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalorieTrackingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS)
public class CalorieTrackingApiTest {

	@LocalServerPort
	private int port;
	
	@Before
	public void setup() {
		RestAssured.port = port;
	}
	
	@Test
	public void testGetFoods() {
		Response response = RestAssured.when().get("/calTracking/public/getFoods");
		
		assertEquals("200 must be returned", HttpStatus.OK.value(), response.statusCode());
	}
	
	@Test
	public void testGetFoodsWithRange() {
		Response response = RestAssured.when().get("/calTracking/public/getFoods/50/100");
		
		assertEquals("200 must be returned", HttpStatus.OK.value(), response.statusCode());
	}
	
	@Test
	public void testGetFoodsWithRangeNotFound() {
		Response response = RestAssured.when().get("/calTracking/public/getfoods/1000/2000");
		
		assertEquals("404 must be returned", HttpStatus.NOT_FOUND.value(), response.statusCode());
	}
	
	@Test
	public void testAddFood() {
		Headers headers = populateAdminHeaders();
		Response response = RestAssured.given()
				.headers(headers)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(this.createMockFood())
				.when().post("/calTracking/admin/addFood");
		
		assertEquals("200 must be returned", HttpStatus.CREATED.value(), response.statusCode());
	}
	
	@Test
	public void testAddFoodWithoutConsumerKey() {
		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(this.createMockFood())
				.when().post("/calTracking/admin/addFood");
		
		assertEquals("200 must be returned", HttpStatus.UNAUTHORIZED.value(), response.statusCode());
	}
	
	/**
	 * 
	 * @return
	 */
	private Headers populateAdminHeaders() {
		return new Headers(new Header("consumer-key", "admin"));
	}
	
	private Food createMockFood() {
		return new Food(Long.valueOf(1000), "Banana", Long.valueOf(2000));
	}
}
