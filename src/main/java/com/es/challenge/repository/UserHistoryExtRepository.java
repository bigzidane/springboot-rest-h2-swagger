package com.es.challenge.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.es.challenge.domain.UserHistoryEntry;

@org.springframework.stereotype.Repository
public interface UserHistoryExtRepository extends ReadOnlyRepository<UserHistoryEntry, Long>{
	
	/**
	 * To fetch data by userId with Food detail
	 * @param userId
	 * @return
	 */
	@Query(value="SELECT u.id, u.user_Id as userId, u.food_Id as foodId, u.date as date, u.calories as calories, f.name as food_name"
			+ " FROM TBL_USER_CALORIE_TRACKING u, TBL_FOOD f "
			+ " WHERE u.user_Id = ?1 AND u.food_Id = f.id", nativeQuery=true)
	List<UserHistoryEntry> searchUserHistory(Long userId);
	
	/**
	 * To fetch data by userId and requestDate with Food detail
	 * @param userId
	 * @param requestDate
	 * @return
	 */
	@Query(value="SELECT u.id, u.user_Id as userId, u.food_Id as foodId, u.date as date, u.calories as calories, f.name as food_name"
			+ " FROM TBL_USER_CALORIE_TRACKING u, TBL_FOOD f "
			+ " WHERE u.user_Id = ?1 AND u.food_Id = f.id AND u.date = ?2", nativeQuery=true)
	List<UserHistoryEntry> searchUserHistory(Long userId, Date requestDate);
}
