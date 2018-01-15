package com.es.challenge.service;

import com.es.challenge.domain.CalorieTrackingRequest;
import com.es.challenge.domain.CalorieViewTrackingRequest;
import com.es.challenge.domain.UserCalorieTrackingResponse;

public interface CalorieTrackingService {
	public void addCalorie(CalorieTrackingRequest trackCalorieRequest);
	public void removeCalorie(CalorieTrackingRequest trackCalorieRequest);
	public UserCalorieTrackingResponse viewCalorie(CalorieViewTrackingRequest trackCalorieRequest);
}
