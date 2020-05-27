package org.dal.cs5308.t20.Project.user;

@SuppressWarnings("serial")
public class DuplicateUserException extends Exception {
	public DuplicateUserException(String message) {
		super(message);
	}
}
