package com.es.challenge.validation.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import com.es.challenge.CalorieTrackingConstant;
import com.es.challenge.domain.CalorieViewTrackingRequest;
import com.es.challenge.validation.CalorieTrackingValidation;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CalorieTrackingValidationImpl implements CalorieTrackingValidation, CalorieTrackingConstant {
	@Override
	public boolean isValidCalorieTrackingRequest(CalorieViewTrackingRequest request) {
		try {
			String date = request.getDate();
			if (!Strings.isEmpty(date)) {
				formatter.parse(date);
			}
			return true;
		}
		catch (Exception e) {
			log.error("There is an exception when perform validation", e);
			return false;
		}
	}
}
