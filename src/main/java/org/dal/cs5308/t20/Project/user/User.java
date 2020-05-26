package org.dal.cs5308.t20.Project.user;

public class User {
	private Long id;
	private String bannerId;
	private String firstName;
	private String lastName;
	private String emailId;
	
	public User(Long id, String bannerId, String firstName, String lastName, String emailId) {
		this.id = id;
		this.bannerId = bannerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public String getBannerId() {
		return bannerId;
	}
}