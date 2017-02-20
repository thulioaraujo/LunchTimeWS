package com.lunchtime.exception;

public class DuplicatedUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicatedUserException() {
	}

	public DuplicatedUserException(String message) {
		super(message);
	}
}
