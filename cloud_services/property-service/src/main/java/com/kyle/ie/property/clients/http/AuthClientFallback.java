package com.kyle.ie.property.clients.http;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.kyle.ie.auth.models.AuthUserPayload;

@Component
public class AuthClientFallback implements IAuthClient {
	
	private static final String ERROR = "Unable to validate token";

	@Override
	public AuthUserPayload auth(@RequestHeader("X-Authorization") String token) {
		return new AuthUserPayload(0, false, ERROR);
	}

}
