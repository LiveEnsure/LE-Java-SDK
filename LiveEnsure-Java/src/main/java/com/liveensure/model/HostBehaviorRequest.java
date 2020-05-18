package com.liveensure.model;

public class HostBehaviorRequest extends AgentConfigRequest{
	
	private String orientation;
	private String touches;

	public HostBehaviorRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HostBehaviorRequest(String sessionToken, String orientation, String touches, String required) {
		super(sessionToken, required);
		this.orientation = orientation;
		this.touches = touches;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getTouches() {
		return touches;
	}

	public void setTouches(String touches) {
		this.touches = touches;
	}

	@Override
	public String toString() {
		return "Behavior [getSessionToken()=" + getSessionToken() + ", orientation=" + orientation + ", touches=" + touches + "]";
	}

}
