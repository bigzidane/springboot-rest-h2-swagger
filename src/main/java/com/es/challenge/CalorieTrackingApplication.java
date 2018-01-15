package com.es.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CalorieTrackingApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CalorieTrackingApplication.class, args);
	}
}
