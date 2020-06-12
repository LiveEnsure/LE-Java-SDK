package com.le.java;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abhinay Gupta
 *
 */
public class Config {

	// --------------------------------------------------------------
	// Standard API Variables [ DO NOT EDIT ]

	static String apiVersion = "5";
	static String pubHostBase = "https://app.liveensure.com/";
	static String leHostBase = pubHostBase + "/live-identity";
	static String leRestUrl = leHostBase + "/rest";
	static String leHostUrl = leRestUrl + "/host";
	static String challengeUrl = leHostUrl + "/challenge";
	static String stackLocation = "US";
	static String log = "";
	static boolean DEBUG = false;

	// --------------------------------------------------------------
	// Developer Variables [ EDIT FOR YOUR ACCOUNT INFO ]

//	static final String AGENT_ID = ""; // your agentID
//	private static final String API_KEY = ""; // your apiKey
//	private static final String API_PASS = ""; // your apiKey
//	static String sessionToken = ""; //your sessionToken

	// --------------------------------------------------------------
	/**
	 * Make a request to get authenticate and get a session token in response
	 * 
	 * @param userId
	 * @param agentId
	 * @param apiKey
	 * @return sessionToken
	 */
	public static Map<String, Object> leStartSession(String userId, String agentId, String apiKey, String ApiPass) {
		
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
	 * Make a request to registered a consumer 
	 * 
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param country
	 * @param mobile
	 * @return registerDetails
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
	 * Make a request to delete an user
	 * 
	 * @param userId
	 * @param agentId
	 * @return deleteUserDetails
	 */
	public static Map<String, Object> deleteUser(String userId, String agentId) {
		
		String params = "userId="+userId+"&agentId="+agentId;
		String requestUrl = new StringBuffer(Config.leHostUrl).append("/user").toString();
		Map<String, Object> responseData = Service.getServiceObject().MakeDeleteRequest(requestUrl, params, "application/x-www-form-urlencoded");
		System.out.println("responseData " + responseData);
			
		return responseData;
	}
	
}
