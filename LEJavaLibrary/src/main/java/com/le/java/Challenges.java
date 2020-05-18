package com.le.java;

import java.util.HashMap;
import java.util.Map;

/**
 * @author user
 *
 */
public class Challenges {
	
//	private static String required = "true"; // Required
	private static String fallback = "0"; // Required If fail, other challenge
	private static String maxAt = "1"; // Max retries

	/**

	 * @param sessionToken
	 * @return
	 */
	public static Map<String, Object> pollStatus(String sessionToken) {
		Map<String, Object> responseData = new HashMap<>();
		System.out.println("Polling for status...");

		if (sessionToken.length() == 0) {
			responseData.put("status", "Invalid token");
			return responseData;
		}
		String requestUrl = new StringBuffer(Config.leHostUrl).append("/session/")
				.append(sessionToken).append("/").append(Config.agentId).toString();

		responseData = Service.getServiceObject().MakeGetRequest(requestUrl);

		System.out.println(responseData);

		return responseData;
	}
	
	/**
	 * @param sessionToken
	 * @param responseData
	 */
	private static void addQrCode(String sessionToken, Map<String, Object> responseData) {
		responseData.put("FileUrl", new StringBuffer(Config.leHostBase).append("/QR?w=240&sessionToken=").append(sessionToken).toString());
		responseData.put("QRUrl", new StringBuffer(Config.leHostBase).append("/launcher?sessionToken=").append(sessionToken).toString());
		responseData.put("LightUrl", new StringBuffer(Config.leHostBase).append("/launcher?sessionToken=").append(sessionToken).append("&light=1").toString());
		responseData.put("LinkUrl", new StringBuffer("liveensure://?sessionToken=").append(sessionToken).append("&status=").append(Config.leHostBase).append("/rest").toString());
	}

	/**
	 * @param sessionToken
	 * @param question
	 * @param answer
	 * @return
	 */
	public static Map<String, Object> addPromptChallenge(String sessionToken, String question, String answer, String required) {

		String type = "PROMPT";

		Map<String, String> challengeDetails = new HashMap<>();
		challengeDetails.put("question", question);
		challengeDetails.put("answer", answer);
		challengeDetails.put("required", required);
		challengeDetails.put("fallbackChallengeID", fallback);
		challengeDetails.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", challengeDetails);

		System.out.println("Adding a PROMPT challenge ..." + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);
		
		System.out.println(responseData);

		return responseData;
	}

	/**
	 * @param sessionToken
	 * @param prompt
	 * @param pin
	 * @return
	 */
	public static Map<String, Object> addPinChallenge(String sessionToken, String prompt, String pin, String required) {

		String type = "PIN";

		Map<String, String> challengeDetails = new HashMap<>();
		challengeDetails.put("prompt", prompt);
		challengeDetails.put("pin", pin);
		challengeDetails.put("required", required);
		challengeDetails.put("fallbackChallengeID", fallback);
		challengeDetails.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", challengeDetails);

		System.out.println("Adding a PIN challenge ..." + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);
		
		System.out.println(responseData);

		return responseData;
	}

	/**
	 * @param sessionToken
	 * @param orientation
	 * @param touches
	 * @return
	 */
	public static Map<String, Object> addHostBehaviorChallenge(String sessionToken, String orientation, String touches, String required) {

		String type = "HOST_BEHAVIOR"; // Required
		String regionCount = "6"; // Grid pattern

		Map<String, String> details = new HashMap<>();
		details.put("orientation", orientation);
		details.put("touches", touches);
		details.put("regionCount", regionCount);
		details.put("required", required);
		details.put("fallbackChallengeID", fallback);
		details.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", details);

		System.out.println("Adding a HOST_BEHAVIOR challenge ..." + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);
		
		System.out.println(responseData);

		return responseData;
	}

	/**
	 * @param sessionToken
	 * @param touches
	 * @return
	 */
	public static Map<String, Object> addHostBehaviorV6Challenge(String sessionToken, String touches, String required) {

		String type = "HOST_BEHAVIOR_V6"; // Required
		String regionCount = "6"; // Grid pattern

		Map<String, String> details = new HashMap<>();
		details.put("touches", touches);
		details.put("regionCount", regionCount);
		details.put("required", required);
		details.put("fallbackChallengeID", fallback);
		details.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", details);

		System.out.println("Adding a HOST_BEHAVIOR_V6 challenge ..." + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);
		
		System.out.println(responseData);

		return responseData;
	}
	
	/**
	 * @param sessionToken
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	public static Map<String, Object> addLatLongChallenge(String sessionToken, String latitude, String longitude, String radius, String required) {

		String type = "LAT_LONG"; // Required

		Map<String, String> details = new HashMap<>();
		details.put("latitude", latitude);
		details.put("longitude", longitude);
		details.put("radius", radius);
		details.put("required", required);
		details.put("fallbackChallengeID", fallback);
		details.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", details);

		System.out.println("Adding LAT_LONG challenge ... " + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);

		System.out.println(responseData);
		
		return responseData;
	}
	
	/**
	 * @param sessionToken
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param inout
	 * @return
	 */
	public static Map<String, Object> addLatLongV6Challenge(String sessionToken, String latitude, String longitude, String radius, String inout, String required) {

		String type = "LAT_LONG_V6"; // Required

		Map<String, String> details = new HashMap<>();
		details.put("latitude", latitude);
		details.put("longitude", longitude);
		details.put("radius", radius);
		details.put("inout", inout);
		details.put("required", required);
		details.put("fallbackChallengeID", fallback);
		details.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", details);

		System.out.println("Adding LAT_LONG_V6 challenge ... " + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);

		System.out.println(responseData);
		
		return responseData;
	}
	
	/**
	 * @param sessionToken
	 * @param touch
	 * @return
	 */
	public static Map<String, Object> addBiometricChallenge(String sessionToken, String touches, String required) {

		String type = "BIOMETRIC";

		Map<String, String> challengeDetails = new HashMap<>();
		challengeDetails.put("touches", touches);
		challengeDetails.put("required", required);
		challengeDetails.put("fallbackChallengeID", fallback);
		challengeDetails.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", challengeDetails);

		System.out.println("Adding a BIOMETRIC challenge ..." + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);
		
		System.out.println(responseData);

		return responseData;
	}
	
	
	/**
	 * @param sessionToken
	 * @param startDate
	 * @param endDate
	 * @param inout
	 * @return
	 */
	public static Map<String, Object> addTimeChallenge(String sessionToken, String startDate, String endDate, String inout, String required) {

		String type = "TIME";

		Map<String, String> challengeDetails = new HashMap<>();
		challengeDetails.put("startDate", startDate);
		challengeDetails.put("endDate", endDate);
		challengeDetails.put("inout", inout);
		challengeDetails.put("required", required);
		challengeDetails.put("fallbackChallengeID", fallback);
		challengeDetails.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", challengeDetails);

		System.out.println("Adding a TIME challenge ..." + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);
		
		System.out.println(responseData);

		return responseData;
	}

	/**
	 * @param sessionToken
	 * @param deviceId
	 * @return
	 */
	public static Map<String, Object> addWearableChallenge(String sessionToken, String deviceId, String required) {

		String type = "WEARABLE";

		Map<String, String> challengeDetails = new HashMap<>();
		challengeDetails.put("deviceId", deviceId);
		challengeDetails.put("required", required);
		challengeDetails.put("fallbackChallengeID", fallback);
		challengeDetails.put("maximumAttempts", maxAt);

		Map<String, Object> data = new HashMap<>();
		data.put("sessionToken", sessionToken);
		data.put("challengeType", type);
		data.put("agentId", Config.agentId);
		data.put("challengeDetails", challengeDetails);

		System.out.println("Adding a WEARABLE challenge ..." + data);

		Map<String, Object> responseData = Service.getServiceObject().MakePutRequest(Config.challengeUrl, data, "application/json");

		addQrCode(sessionToken, responseData);
		
		System.out.println(responseData);

		return responseData;
	}
	
}