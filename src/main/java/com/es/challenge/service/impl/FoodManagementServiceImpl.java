package com.es.challenge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.es.challenge.dao.FoodDao;
import com.es.challenge.domain.Food;
import com.es.challenge.domain.FoodEntity;
import com.es.challenge.service.FoodManagementService;
import com.es.challenge.util.CalorieTrackingUtils;

@Component
public class FoodManagementServiceImpl implements FoodManagementService {

	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private CalorieTrackingUtils calorieTrackingUtils;
	
	@Override
	public Boolean addFood(Food food) {
		return this.foodDao.addFood(this.calorieTrackingUtils.map(food, FoodEntity.class));
	}

	@Override
	public Boolean removeFood(Long foodId) {
		return this.foodDao.removeFood(foodId);
	}
	
	@Override
	public List<Food> getFoods() {
		List<FoodEntity> foods = this.foodDao.getFoods();
		
		return calorieTrackingUtils.mapList(foods, Food.class);
	}

	@Override
	public List<Food> getFoods(Integer caloriesMin, Integer caloriesMax) {
		List<FoodEntity> foods = this.foodDao.getFoods(caloriesMin, caloriesMax);
		
		return calorieTrackingUtils.mapList(foods, Food.class);
	}

}
