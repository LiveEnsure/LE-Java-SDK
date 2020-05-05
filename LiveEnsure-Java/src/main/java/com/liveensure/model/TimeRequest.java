package com.liveensure.model;

public class TimeRequest extends AgentConfigRequest {

	private String startDate;
	private String endDate;
	private String inout;
	
	public TimeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeRequest(String sessionToken, String agentId, String startDate, String endDate, String inout, String required) {
		super(sessionToken, agentId, required);
		this.startDate = startDate;
		this.endDate = endDate;
		this.inout = inout;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getInout() {
		return inout;
	}

	public void setInout(String inout) {
		this.inout = inout;
	}

	@Override
	public String toString() {
		return "Time [getSessionToken()=" + getSessionToken() + ", getAgentId()=" + getAgentId() + ", startDate=" + startDate + ", endDate=" + endDate + ", inout=" + inout + "]";
	}
	
}
