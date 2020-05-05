package com.liveensure.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liveensure.model.ConsumerRequest;
import com.liveensure.service.ConsumerService;

@Controller
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;
	
	@PostMapping("/registerConsumer")
	@ResponseBody
	public Map<String, Object> registerConsumer(@RequestBody ConsumerRequest cr) {
		Map<String, Object> response = consumerService.registerConsumer(cr);
		return response;
	}
}
