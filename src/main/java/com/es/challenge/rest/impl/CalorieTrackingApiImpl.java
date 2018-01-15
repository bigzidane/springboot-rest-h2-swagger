package com.es.challenge.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.es.challenge.domain.CalorieTrackingRequest;
import com.es.challenge.domain.CalorieViewTrackingRequest;
import com.es.challenge.domain.Response;
import com.es.challenge.domain.UserCalorieTrackingResponse;
import com.es.challenge.rest.CalorieTrackingApi;
import com.es.challenge.service.CalorieTrackingService;

@Component
public class CalorieTrackingApiImpl implements CalorieTrackingApi {
	@Autowired
	private CalorieTrackingService calorieTrackingService;
		    
	@Override
	public ResponseEntity addCalorie(@RequestBody CalorieTrackingRequest trackCalorieRequest) {
		this.calorieTrackingService.addCalorie(trackCalorieRequest);
		return new ResponseEntity(new Response(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity removeCalorie(@RequestBody CalorieTrackingRequest trackCalorieRequest) {
		this.calorieTrackingService.removeCalorie(trackCalorieRequest);
		return new ResponseEntity(new Response(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity viewCalorie(@RequestBody CalorieViewTrackingRequest trackCalorieRequest) {
		UserCalorieTrackingResponse userCalorieTrackingResponse = this.calorieTrackingService.viewCalorie(trackCalorieRequest);
		return new ResponseEntity(userCalorieTrackingResponse.getUserCalorieDailyTracking(), HttpStatus.OK);
	}
}
