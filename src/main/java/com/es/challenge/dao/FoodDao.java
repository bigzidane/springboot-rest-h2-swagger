package com.es.challenge.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.es.challenge.domain.FoodEntity;

@Repository
public interface FoodDao {
	List<FoodEntity> getFoods();
	List<FoodEntity> getFoods(Integer caloriesMin, Integer caloriesMax);
	Boolean addFood(FoodEntity food);
	Boolean removeFood(Long foodId);
}
