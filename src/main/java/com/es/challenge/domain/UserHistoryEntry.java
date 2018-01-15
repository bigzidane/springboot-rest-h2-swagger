package com.es.challenge.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryEntry {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	
	private Long foodId;
	
	@Column(name="food_name")
	private String foodName;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="calories")
	private Long calories;
}
