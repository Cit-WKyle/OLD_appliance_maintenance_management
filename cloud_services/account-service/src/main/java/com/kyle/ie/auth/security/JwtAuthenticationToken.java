package com.kyle.ie.auth.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.kyle.ie.auth.security.models.UserContext;
import com.kyle.ie.auth.security.models.token.RawAccessJwtToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final String CANNOT_TRUST_TOKEN = "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead";

	private static final long serialVersionUID = 1L;
	
	private RawAccessJwtToken _rawAccessToken;
	private UserContext _userContext;
	
	public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
		super(null);
		_rawAccessToken = unsafeToken;
	}

	public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		_userContext = userContext;
		super.setAuthenticated(true);
	}
	
	@Override
	public void setAuthenticated(boolean authenticated) {
		if(authenticated) {
			throw new IllegalArgumentException(CANNOT_TRUST_TOKEN);
		}
        super.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return _rawAccessToken;
	}

	@Override
	public Object getPrincipal() {
		return _userContext;
	}
	
	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		_rawAccessToken = null;
	}

}
