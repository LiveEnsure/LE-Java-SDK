package com.liveensure.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.le.java.Config;
import com.liveensure.model.ConsumerRequest;

@Service
public class ConsumerService {

	public Map<String, Object> registerConsumer(ConsumerRequest cr) {
		System.out.println("consumer request =========="+cr);
		Gson gson = new Gson();
		Map<String, Object> responseObj = new HashMap<String, Object>();
		Map<String, Object> responseData = Config.registerConsumer(cr.getEmail(), cr.getFirstName(), cr.getLastName(),
				"US", "123456789");
				
		JSONObject jsonObject = new JSONObject(responseData);
		if (jsonObject.has("consumer")) {
			JSONObject getSth = jsonObject.getJSONObject("consumer");
			Object apiKey = getSth.get("hostSiteWebServiceSignature");
			Object userId = getSth.get("login");
			Object agentPass = getSth.get("hostSiteWebServicePassword");
			Object consumerId = getSth.get("uuid");
			String obj = getSth.get("defaultInstance").toString();
			Map<String, Object> objResponse = gson.fromJson(obj, new TypeToken<HashMap<String, Object>>() {
			}.getType());

			responseObj.put("consumerId", consumerId);
			responseObj.put("agentId", objResponse.get("agentUuid"));
			responseObj.put("apiKey", apiKey);
			responseObj.put("userId", userId);
			responseObj.put("agentPass", agentPass);
		} else {
			responseObj.put("response", responseData.get("response"));
		}
		System.out.println(responseObj);
		return responseObj;
	}
}
