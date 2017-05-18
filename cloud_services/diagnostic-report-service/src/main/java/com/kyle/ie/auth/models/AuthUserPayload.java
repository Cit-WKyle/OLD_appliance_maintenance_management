package com.kyle.ie.auth.models;

public class AuthUserPayload {

	private long _id;
	private boolean _success;
	private String _error;
	
	public AuthUserPayload() {}
	
	public AuthUserPayload(long id, boolean success, String error) {
		_id = id;
		_success = success;
		_error = error;
	}
	
	public long getId() {
		return _id;
	}
	
	public void setId(long id) {
		_id = id;
	}
	
	public boolean getSuccess() {
		return _success;
	}
	
	public void setSuccess(boolean success) {
		_success = success;
	}
	
	public String getError() {
		return _error;
	}
	
	public void setError(String error) {
		_error = error;
	}
}
