package com.kyle.ie.auth.security.models.token;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.Claims;

public class AccessJwtToken implements IJwtToken {
	
	private final String _rawToken;
	
	private Claims _claims;
	
	protected AccessJwtToken(final String token, Claims claims) {
		_rawToken = token;
		_claims = claims;
	}
	
	@Override
	public String getToken() {
		return _rawToken;
	}
	
	@JsonIgnore
	public Claims getClaims() {
		return _claims;
	}

}
