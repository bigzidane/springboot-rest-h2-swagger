package com.es.challenge.domain;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CalorieTrackingRequest extends CalorieViewTrackingRequest {
	@NotEmpty(message="This is a required field")
	private Long foodId;
	
	@NotEmpty(message="This is a required field")
	private Integer calories;
}
