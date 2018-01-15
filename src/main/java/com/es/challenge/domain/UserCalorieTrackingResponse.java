package com.es.challenge.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class UserCalorieTrackingResponse {
	private List<UserCalorieDailyEntry> userCalorieDailyTracking = new ArrayList<UserCalorieDailyEntry>();
	
	public void addUserCalorieDailyEntry(UserCalorieDailyEntry entry) {
		this.userCalorieDailyTracking.add(entry);
	}
}
