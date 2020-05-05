package com.liveensure.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liveensure.model.ConsumerRequest;
import com.liveensure.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> deleteUser(@RequestBody ConsumerRequest cr) {
		return userService.deleteUser(cr);
	}
}
