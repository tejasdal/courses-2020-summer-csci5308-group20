package org.dal.cs5308.t20.Project.user;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String message) {
		super(message);
	}
}