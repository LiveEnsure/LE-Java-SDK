package com.liveensure.model;

import org.springframework.beans.factory.annotation.Value;

public class ConsumerRequest {
	
	private String email;
	private String firstName;
	private String lastName;
	@Value("${api-key}")
	private String apiKey;
	@Value("${agent-id}")
	private String agentId;

	public ConsumerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ConsumerRequest(String firstName, String lastName, String email, String apiKey, String agentId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.apiKey = apiKey;
		this.agentId = agentId;
	}
	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Consumer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", apiKey=" + apiKey
				+ ", agentId=" + agentId + "]";
	}


}
