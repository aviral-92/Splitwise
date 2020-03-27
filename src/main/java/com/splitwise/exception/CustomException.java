package com.splitwise.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public CustomException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomException [message=" + message + "]";
	}

}
