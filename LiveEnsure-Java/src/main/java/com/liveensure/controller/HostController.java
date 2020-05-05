package com.liveensure.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liveensure.model.AgentConfigRequest;
import com.liveensure.model.BiometricRequest;
import com.liveensure.model.ConsumerRequest;
import com.liveensure.model.DeviceRequest;
import com.liveensure.model.HostBehaviorRequest;
import com.liveensure.model.LocationRequest;
import com.liveensure.model.PromptRequest;
import com.liveensure.model.TimeRequest;
import com.liveensure.service.HostService;

@Controller
public class HostController {

	@Autowired
	private HostService hostService;
	
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> Login(@RequestBody ConsumerRequest cr) {
		return hostService.Login(cr);
	}
	
	@PostMapping("/challengeStatus")
	@ResponseBody
	public Map<String, Object> pollStatus(@RequestBody AgentConfigRequest acr) throws Exception {
		return hostService.pollStatus(acr);
	}
	
	@PostMapping("/promptChallenge")
	@ResponseBody
	public Map<String, Object> promptChallenge(@RequestBody PromptRequest pr) {
		return hostService.promptChallenge(pr);
	}
	
	@PostMapping("/hostBehaviorChallenge")
	@ResponseBody
	public Map<String, Object> hostBehaviorChallenge(@RequestBody HostBehaviorRequest br) {
		return hostService.hostBehaviorChallenge(br);
	}

	@PostMapping("/hostBehaviorV6Challenge")
	@ResponseBody
	public Map<String, Object> hostBehaviorV6Challenge(@RequestBody HostBehaviorRequest br) {
		return hostService.hostBehaviorV6Challenge(br);
	}
	
	@PostMapping("/locationChallenge")
	@ResponseBody
	public Map<String, Object> locationChallenge(@RequestBody LocationRequest lr) {
		return hostService.latLongChallenge(lr);
	}

	@PostMapping("/locationV6Challenge")
	@ResponseBody
	public Map<String, Object> locationV6Challenge(@RequestBody LocationRequest lr) {
		return hostService.latLongV6Challenge(lr);
	}
	
	@PostMapping("/biometricChallenge")
	@ResponseBody
	public Map<String, Object> biometricChallenge(@RequestBody BiometricRequest br) {
		return hostService.biometricChallenge(br);
	}
	
	@PostMapping("/timeChallenge")
	@ResponseBody
	public Map<String, Object> timeChallenge(@RequestBody TimeRequest tr) {
		return hostService.timeChallenge(tr);
	}
	
	@PostMapping("/tokenRequest")
	@ResponseBody
	public Map<String, Object> device(@RequestBody DeviceRequest dr) throws Exception {
		return hostService.device(dr);
	}
}