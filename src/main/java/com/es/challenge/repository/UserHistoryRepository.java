package com.es.challenge.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.es.challenge.domain.UserHistoryEntity;

public interface UserHistoryRepository extends JpaRepository<UserHistoryEntity, Long>{
	
	/**
	 * To fetch data by userId
	 * @param userId
	 * @return
	 */
	@Query("SELECT userHistory FROM UserHistoryEntity userHistory WHERE userHistory.userId = :userId")
	List<UserHistoryEntity> searchUserHistory(@Param("userId") Long userId);
	
	/**
	 * To fetch data by userId and requestDate
	 * @param userId
	 * @param requestDate
	 * @return
	 */
	@Query("SELECT userHistory FROM UserHistoryEntity userHistory "
			+ "WHERE userHistory.userId = :userId AND "
			+ "userHistory.date = :requestDate" )
	List<UserHistoryEntity> searchUserHistory(@Param("userId") Long userId, @Param("requestDate") Date requestDate);
}
