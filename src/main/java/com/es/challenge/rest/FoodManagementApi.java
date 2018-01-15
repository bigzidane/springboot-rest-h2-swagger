package com.es.challenge.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.es.challenge.domain.Food;
import com.es.challenge.domain.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public interface FoodManagementApi {
	@GetMapping(value = "/public/getFoods", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all Foods with details", notes="This is a public API", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity getFoods();
	
	@GetMapping(value = "/public/getFoods/{caloriesMin}/{caloriesMax}", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all Foods with details by calories range", notes="This is a public API", response=List.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any food by calories range")
	})
	ResponseEntity getFoods(@PathVariable("caloriesMin") Integer caloriesMin, @PathVariable("caloriesMax") Integer caloriesMax);
	
	@PostMapping(value = "/admin/addFood", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add a new Food with details", notes="This is a public API with admin right", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_CREATED, message = "A new food has been added successfully"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key")
	})
	ResponseEntity addFood(@RequestHeader(name="Consumer-Key", required=false) String key, @RequestBody Food food);
	
	@DeleteMapping(value = "/admin/removeFood/{id}")
	@ApiOperation(value="Remove an existing Food", notes="This is a public API with admin right", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "The food has been removed successfully"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "The Food does not exist")
	})
	ResponseEntity removeFood(@RequestHeader(name="Consumer-Key", required=false) String key, @PathVariable("id") Long foodId);
}
