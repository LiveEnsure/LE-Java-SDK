package com.liveensure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityLEController {

	@GetMapping("/")
	public String StartApp() {
		return "index";
	}

	@GetMapping("/register")
	public String registerApp() {
		return "register";
	}

	@GetMapping("/version")
	@ResponseBody
	public String Version() {
		return "{\"version\":\"6.0.0\", \"serverHost\":\"staging.liveensure.com\"}";
	}

}
