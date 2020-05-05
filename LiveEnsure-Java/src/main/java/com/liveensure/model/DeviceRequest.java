package com.liveensure.model;

import java.util.Map;

public class DeviceRequest extends ConsumerRequest{

	private String challengeType;
	private Map<String, Object> challengeDetails;

	public DeviceRequest() {
		super();
	}

	public DeviceRequest(String firstName, String lastName, String email, String apiKey, String agentId, String challengeType, Map<String, Object> challengeDetails) {
		super(firstName, lastName, email, apiKey, agentId);
		this.challengeType = challengeType;
		this.challengeDetails = challengeDetails;
	}
	
	public String getChallengeType() {
		return challengeType;
	}

	public void setChallengeType(String challengeType) {
		this.challengeType = challengeType;
	}
	
	public Map<String, Object> getChallengeDetails() {
		return challengeDetails;
	}

	public void setChallengeDetails(Map<String, Object> challengeDetails) {
		this.challengeDetails = challengeDetails;
	}

	@Override
	public String toString() {
		return "DeviceRequest [getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()=" + getEmail() + ", getApiKey()=" + getApiKey()
				+ ", getAgentId()=" + getAgentId() + ", challengeType=" + challengeType + ", challengeDetails=" + challengeDetails +"]";
	}
	
}
