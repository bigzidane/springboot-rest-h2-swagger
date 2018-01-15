package com.es.challenge.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.es.challenge.dao.CalorieTrackingDao;
import com.es.challenge.domain.UserHistoryEntity;
import com.es.challenge.domain.UserHistoryEntry;
import com.es.challenge.repository.UserHistoryExtRepository;
import com.es.challenge.repository.UserHistoryRepository;

@Component
public class CalorieTrackingDaoImpl implements CalorieTrackingDao {

	@Autowired
	private UserHistoryRepository userHistoryRepository;
	
	@Autowired
	private UserHistoryExtRepository userHistoryExtRepository;
	
	@Override
	public UserHistoryEntity addUserHistory(UserHistoryEntity userHistoryEntity) {
		return this.userHistoryRepository.save(userHistoryEntity);
	}
	
	@Override
	public List<UserHistoryEntry> viewUserHistory(Long userId) {
		return viewUserHistory(userId, null);
	}
	
	@Override
	public List<UserHistoryEntry> viewUserHistory(Long userId, Date date) {
		return Objects.isNull(date) 
			? this.userHistoryExtRepository.searchUserHistory(userId)
			: this.userHistoryExtRepository.searchUserHistory(userId, date);
	}
}