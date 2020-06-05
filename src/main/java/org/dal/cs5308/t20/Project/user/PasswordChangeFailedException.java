package org.dal.cs5308.t20.Project.user;

@SuppressWarnings("serial")
public class PasswordChangeFailedException extends Exception {
	public PasswordChangeFailedException(String message) {
		super(message);
	}
}
