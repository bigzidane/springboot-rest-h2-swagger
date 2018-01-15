package com.es.challenge.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class UserCalorieDailyEntry {
	@JsonProperty(value="entries")
	private List<UserCalorieDetailEntry> userDailyEntries = new ArrayList<UserCalorieDetailEntry>();
    private String date;
    private Long total;
    
    public void addUserCalorieDetailEntry(UserCalorieDetailEntry entry) {
    	this.userDailyEntries.add(entry);
    }
}
