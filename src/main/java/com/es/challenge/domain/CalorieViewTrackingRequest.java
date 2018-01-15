package com.es.challenge.domain;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CalorieViewTrackingRequest {
	@NotEmpty(message="This is a required field")
	private Long userId;

	private String date;	// format as MMddyyyy
}
