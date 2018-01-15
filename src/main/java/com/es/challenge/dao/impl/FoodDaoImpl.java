package com.es.challenge.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.es.challenge.dao.FoodDao;
import com.es.challenge.domain.FoodEntity;
import com.es.challenge.repository.FoodRepository;

@Component
public class FoodDaoImpl implements FoodDao {

	@Autowired
	private FoodRepository foodRepository;
	
	@Override
	public List<FoodEntity> getFoods() {
		return this.foodRepository.findAll();
	}

	@Override
	public List<FoodEntity> getFoods(Integer caloriesMin, Integer caloriesMax) {
		return this.foodRepository.searchFoodByRange(caloriesMin, caloriesMax);
	}

	@Override
	public Boolean addFood(FoodEntity food) {
		this.foodRepository.save(food);
		return true;
	}

	@Override
	public Boolean removeFood(Long foodId) {
		if (this.foodRepository.exists(foodId)) {
			this.foodRepository.delete(foodId);
			return true;
		}
		else {
			return false;
		}
	}
}
