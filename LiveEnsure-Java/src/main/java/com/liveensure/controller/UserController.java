package com.liveensure.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liveensure.model.ConsumerRequest;
import com.liveensure.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/delete")
	public Map<String, Object> deleteUser(@RequestBody ConsumerRequest cr) {
//		cr.setAgentId(agentId);cr.setApiKey(apiKey);
		return userService.deleteUser(cr);
	}
}
