package com.liveensure.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.le.java.Challenges;
import com.le.java.Config;
import com.liveensure.model.AgentConfigRequest;
import com.liveensure.model.BiometricRequest;
import com.liveensure.model.ConsumerRequest;
import com.liveensure.model.DeviceRequest;
import com.liveensure.model.HostBehaviorRequest;
import com.liveensure.model.LocationRequest;
import com.liveensure.model.PromptRequest;
import com.liveensure.model.TimeRequest;
import com.liveensure.model.WearableRequest;

@Service
public class HostService {

	public Map<String, Object> Login(ConsumerRequest cr) {
		Map<String, Object> responseData = Config.leStartSession(cr.getEmail(), cr.getAgentId(), cr.getApiKey());
		return responseData;
	}

	public Map<String, Object> pollStatus(AgentConfigRequest acr) throws Exception {
		return Challenges.pollStatus(acr.getAgentId(), acr.getSessionToken());
	}

	public Map<String, Object> promptChallenge(PromptRequest pr) {
		return Challenges.addPromptChallenge(pr.getSessionToken(), pr.getAgentId(), pr.getQues(), pr.getAns(),
				pr.getRequired());
	}
	
	public Map<String, Object> hostBehaviorChallenge(HostBehaviorRequest br) {
		return Challenges.addHostBehaviorChallenge(br.getSessionToken(), br.getAgentId(), br.getOrientation(), br.getTouches(),
				br.getRequired());
	}

	public Map<String, Object> hostBehaviorV6Challenge(HostBehaviorRequest br) {
		return Challenges.addHostBehaviorV6Challenge(br.getSessionToken(), br.getAgentId(), br.getTouches(),
				br.getRequired());
	}
	
	public Map<String, Object> latLongChallenge(LocationRequest lr) {
		return Challenges.addLatLongChallenge(lr.getSessionToken(), lr.getAgentId(), lr.getLatitude(),
				lr.getLongitude(), lr.getRadius(), lr.getRequired());
	}

	public Map<String, Object> latLongV6Challenge(LocationRequest lr) {
		return Challenges.addLatLongV6Challenge(lr.getSessionToken(), lr.getAgentId(), lr.getLatitude(),
				lr.getLongitude(), lr.getRadius(), lr.getInout(), lr.getRequired());
	}

	public Map<String, Object> biometricChallenge(BiometricRequest br) {
		return Challenges.addBiometricChallenge(br.getSessionToken(), br.getAgentId(), br.getTouches(),
				br.getRequired());
	}

	public Map<String, Object> timeChallenge(TimeRequest tr) {
		return Challenges.addTimeChallenge(tr.getSessionToken(), tr.getAgentId(), tr.getStartDate(), tr.getEndDate(),
				tr.getInout(), tr.getRequired());
	}

	public Map<String, Object> wearableChallenge(WearableRequest wr) {
		return Challenges.addWearableChallenge(wr.getSessionToken(), wr.getAgentId(), wr.getDeviceId(),
				wr.getRequired());
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> device(DeviceRequest dr) throws Exception {
		Map<String, Object> responseData = Login(dr);
		System.out.println(dr);
		if ("HOST".equalsIgnoreCase(dr.getChallengeType())) {
			if (dr.getChallengeDetails().containsKey("PROMPT")) {
				PromptRequest pr = new PromptRequest();
				pr.setSessionToken((String) responseData.get("sessionToken"));
				pr.setAgentId(dr.getAgentId());
				Map<String, Object> map = (HashMap<String, Object>) dr.getChallengeDetails().get("PROMPT");
				pr.setQues((String) map.get("question"));
				pr.setAns((String) map.get("answer"));
				pr.setRequired((String) map.get("required"));
				responseData.putAll(promptChallenge(pr));
			}
			if (dr.getChallengeDetails().containsKey("LAT_LONG_V6")) {
				LocationRequest lr = new LocationRequest();
				lr.setSessionToken((String) responseData.get("sessionToken"));
				lr.setAgentId(dr.getAgentId());
				Map<String, Object> map = (HashMap<String, Object>) dr.getChallengeDetails().get("LAT_LONG");
				lr.setLatitude((String) map.get("latitude"));
				lr.setLongitude((String) map.get("longitude"));
				lr.setRadius((String) map.get("radius"));
				lr.setInout((String) map.get("inout"));
				lr.setRequired((String) map.get("required"));
				responseData.putAll(latLongV6Challenge(lr));
			}
			if (dr.getChallengeDetails().containsKey("TIME")) {
				TimeRequest tr = new TimeRequest();
				tr.setSessionToken((String) responseData.get("sessionToken"));
				tr.setAgentId(dr.getAgentId());
				Map<String, Object> map = (HashMap<String, Object>) dr.getChallengeDetails().get("TIME");
				tr.setStartDate((String) map.get("startDate"));
				tr.setEndDate((String) map.get("endDate"));
				tr.setInout((String) map.get("inout"));
				tr.setRequired((String) map.get("required"));
				responseData.putAll(timeChallenge(tr));
			} 
			if (!dr.getChallengeDetails().containsKey("PROMPT") && !dr.getChallengeDetails().containsKey("LAT_LONG_V6") && !dr.getChallengeDetails().containsKey("TIME")) {
			}
		} else if ("USER".equalsIgnoreCase(dr.getChallengeType())) {
			if (dr.getChallengeDetails().containsKey("HOST_BEHAVIOR_V6")) {
				HostBehaviorRequest br = new HostBehaviorRequest();
				br.setSessionToken((String) responseData.get("sessionToken"));
				br.setAgentId(dr.getAgentId());
				Map<String, Object> map = (HashMap<String, Object>) dr.getChallengeDetails().get("HOST_BEHAVIOR");
//				br.setOrientation("0");
				br.setTouches((String) map.get("touches"));
				br.setRequired((String) map.get("required"));
				responseData.putAll(hostBehaviorV6Challenge(br));
			}
			if (dr.getChallengeDetails().containsKey("BIOMETRIC")) {
				BiometricRequest bior = new BiometricRequest();
				bior.setSessionToken((String) responseData.get("sessionToken"));
				bior.setAgentId(dr.getAgentId());
				Map<String, Object> map = (HashMap<String, Object>) dr.getChallengeDetails().get("BIOMETRIC");
				bior.setTouches((String) map.get("touches"));
				bior.setRequired((String) map.get("required"));
				responseData.putAll(biometricChallenge(bior));
			}
			if (dr.getChallengeDetails().containsKey("WEARABLE")) {
				WearableRequest wr = new WearableRequest();
				wr.setSessionToken((String) responseData.get("sessionToken"));
				wr.setAgentId(dr.getAgentId());
				Map<String, Object> map = (HashMap<String, Object>) dr.getChallengeDetails().get("WEARABLE");
				wr.setDeviceId((String) map.get("deviceId"));
				wr.setRequired((String) map.get("required"));
				responseData.putAll(wearableChallenge(wr));
			}
			if (!dr.getChallengeDetails().containsKey("HOST_BEHAVIOR_V6") && !dr.getChallengeDetails().containsKey("BIOMETRIC") && !dr.getChallengeDetails().containsKey("WEARABLE")) {
			}
		} else if ("KNOWLEDGE".equalsIgnoreCase(dr.getChallengeType())) {
			PromptRequest pr = new PromptRequest();
			pr.setSessionToken((String) responseData.get("sessionToken"));
			pr.setAgentId(dr.getAgentId());
			pr.setQues((String) dr.getChallengeDetails().get("question"));
			pr.setAns((String) dr.getChallengeDetails().get("answer"));
			pr.setRequired("true");
			responseData.putAll(promptChallenge(pr));
		} else if ("HOST_BEHAVIOR_V6".equalsIgnoreCase(dr.getChallengeType())) {
			HostBehaviorRequest br = new HostBehaviorRequest();
			br.setSessionToken((String) responseData.get("sessionToken"));
			br.setAgentId(dr.getAgentId());
//			br.setOrientation("0");
			br.setTouches((String) dr.getChallengeDetails().get("touches"));
			br.setRequired("true");
			responseData.putAll(hostBehaviorV6Challenge(br));
		} else if ("LAT_LONG_V6".equalsIgnoreCase(dr.getChallengeType())) {
			LocationRequest lr = new LocationRequest();
			lr.setSessionToken((String) responseData.get("sessionToken"));
			lr.setAgentId(dr.getAgentId());
			lr.setLatitude((String) dr.getChallengeDetails().get("latitude"));
			lr.setLongitude((String) dr.getChallengeDetails().get("longitude"));
			lr.setRadius((String) dr.getChallengeDetails().get("radius"));
			lr.setInout((String) dr.getChallengeDetails().get("inout"));
			lr.setRequired("true");
			responseData.putAll(latLongV6Challenge(lr));
		} else if ("BIOMETRIC".equalsIgnoreCase(dr.getChallengeType())) {
			BiometricRequest br = new BiometricRequest();
			br.setSessionToken((String) responseData.get("sessionToken"));
			br.setAgentId(dr.getAgentId());
			br.setTouches("true");
			br.setRequired("true");
			responseData.putAll(biometricChallenge(br));
		} else if ("TIME".equalsIgnoreCase(dr.getChallengeType())) {
			TimeRequest tr = new TimeRequest();
			tr.setSessionToken((String) responseData.get("sessionToken"));
			tr.setAgentId(dr.getAgentId());
			tr.setStartDate((String) dr.getChallengeDetails().get("startDate"));
			tr.setEndDate((String) dr.getChallengeDetails().get("endDate"));
			tr.setInout((String) dr.getChallengeDetails().get("inout"));
			tr.setRequired("true");
			responseData.putAll(timeChallenge(tr));
		} else if ("WEARABLE".equalsIgnoreCase(dr.getChallengeType())) {
			WearableRequest wr = new WearableRequest();
			wr.setSessionToken((String) responseData.get("sessionToken"));
			wr.setAgentId(dr.getAgentId());
			wr.setDeviceId((String) dr.getChallengeDetails().get("deviceId"));
			wr.setRequired("true");
			responseData.putAll(wearableChallenge(wr));
		} else {

		}
		return responseData;
	}
}
