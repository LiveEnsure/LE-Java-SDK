package com.liveensure.model;

public class WearableRequest extends AgentConfigRequest {

	private String deviceId;
	
	public WearableRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WearableRequest(String sessionToken, String agentId, String deviceId, String required) {
		super(sessionToken, agentId, required);
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "Wearable [getSessionToken()=" + getSessionToken() + ", getAgentId()=" + getAgentId() + ", deviceId=" + deviceId+ "]";
	}
	
}
