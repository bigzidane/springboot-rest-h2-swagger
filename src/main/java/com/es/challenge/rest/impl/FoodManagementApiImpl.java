package com.es.challenge.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.es.challenge.domain.Food;
import com.es.challenge.domain.Response;
import com.es.challenge.rest.FoodManagementApi;
import com.es.challenge.service.ESAuthService;
import com.es.challenge.service.FoodManagementService;

@Component
public class FoodManagementApiImpl implements FoodManagementApi {

	@Autowired
	private FoodManagementService foodManagementService;
	
	@Autowired
	private ESAuthService esAuthService;
	
	@Override
	public ResponseEntity getFoods() {
		List<Food> foods = this.foodManagementService.getFoods();
		return new ResponseEntity(foods, HttpStatus.OK);
	}

	@Override
	public ResponseEntity getFoods(@PathVariable("caloriesMin") Integer caloriesMin, @PathVariable("caloriesMax") Integer caloriesMax) {
		List<Food> foods = this.foodManagementService.getFoods(caloriesMin, caloriesMax);
		return new ResponseEntity(foods, !foods.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity addFood(@RequestHeader(name="Consumer-Key", required=false) String key, @RequestBody Food food) {
		if (this.esAuthService.isAdmin(key)) {
			Boolean result = this.foodManagementService.addFood(food);
			return new ResponseEntity(result, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity(new Response("Invalid Consmer Key"), HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public ResponseEntity removeFood(@RequestHeader(name="Consumer-Key", required=false) String key, @PathVariable("id") Long foodId) {
		if (this.esAuthService.isAdmin(key)) {
			Boolean result = this.foodManagementService.removeFood(foodId);
			return new ResponseEntity(result, (result) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity(new Response("Invalid Consmer Key"), HttpStatus.UNAUTHORIZED);
		}
	}
}
