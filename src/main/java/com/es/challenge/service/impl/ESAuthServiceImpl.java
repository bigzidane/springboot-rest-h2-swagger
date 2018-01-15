package com.es.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.es.challenge.service.ESAuthService;

@Service
public class ESAuthServiceImpl implements ESAuthService {

	@Override
	public boolean isAdmin(String key) {
		return "admin".equals(key);
	}

}
