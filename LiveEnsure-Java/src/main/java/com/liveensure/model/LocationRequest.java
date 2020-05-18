package com.liveensure.model;

public class LocationRequest extends AgentConfigRequest {
	
	private String latitude;
	private String longitude;
	private String radius;
	private String inout;

	public LocationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationRequest(String sessionToken, String latitude, String longitude, String radius, String inout, String required) {
		super(sessionToken, required);
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.inout = inout;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}
	
	public String getInout() {
		return inout;
	}

	public void setInout(String inout) {
		this.inout = inout;
	}

	@Override
	public String toString() {
		return "Location [getSessionToken()=" + getSessionToken() + ", latitude=" + latitude + ", longitude=" + longitude + ", radius=" + radius + ", inout=" + inout + "]";
	}

}
