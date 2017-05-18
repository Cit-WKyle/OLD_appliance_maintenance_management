package com.kyle.ie.auth.models;

public class AuthUserPayload {

	private long _id;
	private boolean _success;
	private String _error;
	
	private String _type;
	
	public AuthUserPayload(long id, String type, boolean success, String error) {
		_id = id;
		_success = success;
		_error = error;
		_type = type;
	}
	
	public long getId() {
		return _id;
	}
	
	public boolean getSuccess() {
		return _success;
	}
	
	public String getError() {
		return _error;
	}
	
	public String getType() {
		return _type;
	}
}
