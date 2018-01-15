package com.es.challenge.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.es.challenge.domain.CalorieTrackingRequest;
import com.es.challenge.domain.CalorieViewTrackingRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public interface CalorieTrackingApi {

	@PostMapping(value = "/public/trackCalorie", 
			produces= {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all Activities of the user. It also can return Activities of the user on a specific date.", notes="This is a public API", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity viewCalorie(@RequestBody CalorieViewTrackingRequest trackCalorieRequest);
	
	@PutMapping(value = "/public/trackCalorie",
			produces= {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add an activity of the user on the current date as default. It also can be on a specific date.", notes="This is a public API", response=String.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity addCalorie(@RequestBody CalorieTrackingRequest trackCalorieRequest);
	
	@DeleteMapping(value = "/public/trackCalorie",
			produces= {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Update (not Delete) an activity of the user on the current date as default. It also can be on a specific date.", notes="This is a public API", response=String.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity removeCalorie(@RequestBody CalorieTrackingRequest trackCalorieRequest);
}