package com.es.challenge.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.es.challenge.domain.UserHistoryEntity;
import com.es.challenge.domain.UserHistoryEntry;

@Repository
public interface CalorieTrackingDao {
	UserHistoryEntity addUserHistory(UserHistoryEntity userHistoryEntity);
	
	List<UserHistoryEntry> viewUserHistory(Long userId);
	List<UserHistoryEntry> viewUserHistory(Long userId, Date date);
}
