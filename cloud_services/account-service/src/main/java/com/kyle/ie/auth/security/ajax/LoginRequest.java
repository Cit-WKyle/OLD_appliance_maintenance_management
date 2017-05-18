package com.kyle.ie.auth.security.ajax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
	private String _username;
	private String _password;

    @JsonCreator
    public LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        _username = username;
        _password = password;
    }
    
    public String getUsername() {
    	return _username;
    }
    
    public String getPassword() {
    	return _password;
    }
}
