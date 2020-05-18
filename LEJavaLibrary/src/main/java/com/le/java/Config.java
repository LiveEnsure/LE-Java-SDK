package com.le.java;

import java.util.HashMap;
import java.util.Map;

/**
 * @author user
 *
 */
public class Config {

	// --------------------------------------------------------------
	// Standard API Variables [ DO NOT EDIT ]

	static String apiVersion = "5";
	static String pubHostBase = "https://staging.liveensure.com";//"https://liveensure.damcogroup.com:8443";//"https://app26.liveensure.com/";
	static String leHostBase = pubHostBase + "/live-identity";
	static String leRestUrl = leHostBase + "/rest";
	static String leHostUrl = leRestUrl + "/host";
	static String challengeUrl = leHostUrl + "/challenge";
	static String stackLocation = "US";
//	static String log = "";
	static boolean DEBUG = false;

	// --------------------------------------------------------------
	// Developer Variables [ EDIT FOR YOUR ACCOUNT INFO ]

	static final String agentId = "6958a370-1d27-43d1-9564-d3eb3f4dcbfb"; // your agentID
	private static final String apiKey = "28ebfd62-874b-4254-9148-ce26a4d46cc2"; // your apiKey
//	static String sessionToken = "";
	
//	Staging server credential mr.abhinaygupta@gmail.com
//	#agent-id=6958a370-1d27-43d1-9564-d3eb3f4dcbfb
//	#api-key=28ebfd62-874b-4254-9148-ce26a4d46cc2
//	#api-password=jL34Q0FyY65YMKeO
	
//	Staging server credential client
//	#agent-id=d7a20345-2e25-48fe-8617-external
//	#api-key=6b582cbd-687a-4b5f-a921-905e84354cef0
//	#api-password=JtvamAMqhpIrAuBB10
	
//	local server credential mr.abhinaygupta@gmail.com
//	agent-id=31dbe2a0-fa64-46d0-96f0-2019c7143b23
//	api-key=a139dd70-9923-497d-aead-5b33d6907055
//	api-password=VxCjrFTTqwoqNsyr

	// --------------------------------------------------------------
	/**
	 * Make an request to get authenticate and get a sessionToken in Response
	 * 
	 * @param userId
	 * @param agentId
	 * @param apiKey
	 * @return
	 */
	public static Map<String, Object> leStartSession(String userId) {
		
		System.out.println("Start Session Called......................");
		Map<String, Object> loginParams = new HashMap<>();
		loginParams.put("apiVersion", apiVersion);
		loginParams.put("userId", userId);
		loginParams.put("agentId", agentId);
		loginParams.put("apiKey", apiKey);

		String requestUrl = new StringBuffer(leHostUrl).append("/session").toString();
		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(requestUrl, loginParams, "application/json");
		responseData.put("sessionHost", leRestUrl);
		System.out.println("responseData " + responseData);
		
		return responseData;
	}
	
	/**
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param country
	 * @param mobile
	 * @return
	 */
	public static Map<String, Object> registerConsumer(String email, String firstName, String lastName, String country, String mobile) {
		
		Map<String, Object> loginParams = new HashMap<>();
		loginParams.put("email", email);
		loginParams.put("firstName", firstName);
		loginParams.put("lastName", lastName);
		loginParams.put("country", country);
		loginParams.put("mobile", mobile);

		String requestUrl = new StringBuffer(pubHostBase).append("/live-consumer-pvt/admin/consumer/register").toString();
		Map<String, Object> responseData = Service.getServiceObject().MakePostRequest(requestUrl, loginParams, "application/json");
		System.out.println("responseData " + responseData);
			
		return responseData;
	}
	
	
	/**
	 * @param userId
	 * @param agentId
	 * @return
	 */
	public static Map<String, Object> deleteUser(String userId) {
		
		String params = "userId="+userId+"&agentId="+agentId;

		String requestUrl = new StringBuffer(Config.leHostUrl).append("/user").toString();
		Map<String, Object> responseData = Service.getServiceObject().MakeDeleteRequest(requestUrl, params, "application/x-www-form-urlencoded");
		System.out.println("responseData " + responseData);
			
		return responseData;
	}
	
}
