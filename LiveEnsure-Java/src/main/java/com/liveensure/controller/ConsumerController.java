package com.liveensure.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liveensure.model.ConsumerRequest;
import com.liveensure.service.ConsumerService;

@RestController
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;
	
	@PostMapping("/registerConsumer")
	public Map<String, Object> registerConsumer(@RequestBody ConsumerRequest cr) {
		Map<String, Object> response = consumerService.registerConsumer(cr);
		return response;
	}
}
