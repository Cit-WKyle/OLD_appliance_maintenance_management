package com.kyle.ie.auth.security.models;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;

public class UserContext {
	
	private static final String BLANK_USERNAME = "Username is blank: ";

	private final String _username;
	private final String _userType;
	private final List<GrantedAuthority> _authorities;
	
    private UserContext(String username, List<GrantedAuthority> authorities, String userType) {
        _username = username;
        _authorities = authorities;
        _userType = userType;
    }
    
    public static UserContext create(String username, List<GrantedAuthority> authorities, String userType) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException(BLANK_USERNAME + username);
        return new UserContext(username, authorities, userType);
    }

    public String getUsername() {
        return _username;
    }
    
    public String getType() {
    	return _userType;
    }

    public List<GrantedAuthority> getAuthorities() {
        return _authorities;
    }
}
