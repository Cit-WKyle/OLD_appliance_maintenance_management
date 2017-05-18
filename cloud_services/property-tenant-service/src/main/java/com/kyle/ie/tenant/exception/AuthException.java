package com.kyle.ie.tenant.exception;

public class AuthException extends Exception {

	private static final long serialVersionUID = 1L;

	public AuthException(String err) {
		super(err);
	}
}
