package com.liveensure.model;

public class BiometricRequest extends AgentConfigRequest {

	private String touches;
	
	public BiometricRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BiometricRequest(String sessionToken, String touches, String required) {
		super(sessionToken, required);
		this.touches = touches;
	}

	public String getTouches() {
		return touches;
	}

	public void setTouches(String touches) {
		this.touches = touches;
	}

	@Override
	public String toString() {
		return "Biometric [getSessionToken()=" + getSessionToken() + ", touch=" + touches + "]";
	}
	
}
