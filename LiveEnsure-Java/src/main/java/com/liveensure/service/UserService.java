package com.liveensure.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.le.java.Config;
import com.liveensure.model.ConsumerRequest;

@Service
public class UserService {

	public Map<String, Object> deleteUser(ConsumerRequest cr) {
		return Config.deleteUser(cr.getEmail(), cr.getAgentId());
	}
}
