package com.liveensure.model;

public class AgentConfigRequest {
	
	private String sessionToken;
	private String required;
	
	public AgentConfigRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgentConfigRequest(String sessionToken, String required) {
		this.sessionToken = sessionToken;
		this.required = required;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	@Override
	public String toString() {
		return "AgentConfigRequest [sessionToken=" + sessionToken + ", required=" + required + "]";
	}

}
