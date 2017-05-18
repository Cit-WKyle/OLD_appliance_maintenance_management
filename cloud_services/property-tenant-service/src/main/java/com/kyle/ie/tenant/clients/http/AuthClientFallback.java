package com.kyle.ie.tenant.clients.http;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.auth.models.UserDetails;

@Component
public class AuthClientFallback implements IAuthClient {
	
	private static final String ERROR = "Unable to validate token";

	@Override
	public AuthUserPayload auth(@RequestHeader("X-Authorization") String token) {
		return new AuthUserPayload(0, "", false, ERROR);
	}

	@Override
	public UserDetails user(String token) {
		return new UserDetails();
	}

}
