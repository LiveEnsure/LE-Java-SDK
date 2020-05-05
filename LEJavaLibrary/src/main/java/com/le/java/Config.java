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
	static String pubHostBase = "https://staging.liveensure.com";//"https://liveensure.damcogroup.com:8443";//"http://172.29.100.187:8080";//"https://app.liveensure.com/";
	static String leHostBase = pubHostBase + "/live-identity";
	static String leRestUrl = leHostBase + "/rest";
	static String leHostUrl = leRestUrl + "/host";
	static String challengeUrl = leHostUrl + "/challenge";
	static String stackLocation = "US";
//	static String log = "";
	static boolean DEBUG = false;

	// --------------------------------------------------------------
	// Developer Variables [ EDIT FOR YOUR ACCOUNT INFO ]

//	static String agentId = ""; // your agentID
//	static String sessionToken = "";

	// --------------------------------------------------------------
	/**
	 * Make an request to get authenticate and get a sessionToken in Response
	 * 
	 * @param userId
	 * @param agentId
	 * @param apiKey
	 * @return
	 */
	public static Map<String, Object> leStartSession(String userId, String agentId, String apiKey) {
		
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
	public static Map<String, Object> deleteUser(String userId, String agentId) {
		
		String params = "userId="+userId+"&agentId="+agentId;

		String requestUrl = new StringBuffer(Config.leHostUrl).append("/user").toString();
		Map<String, Object> responseData = Service.getServiceObject().MakeDeleteRequest(requestUrl, params, "application/x-www-form-urlencoded");
		System.out.println("responseData " + responseData);
			
		return responseData;
	}
	
}
