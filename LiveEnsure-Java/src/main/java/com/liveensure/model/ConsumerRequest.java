package com.liveensure.model;

public class ConsumerRequest {
	
	private String email;
	private String firstName;
	private String lastName;

	public ConsumerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ConsumerRequest(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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
		return "Consumer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}


}
