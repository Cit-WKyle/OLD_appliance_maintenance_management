package com.kyle.ie.auth.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.kyle.ie.auth.security.models.token.IJwtToken;

public class JwtExpiredTokenException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

    private IJwtToken _token;

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public JwtExpiredTokenException(IJwtToken token, String msg, Throwable t) {
        super(msg, t);
        _token = token;
    }

    public String token() {
        return _token.getToken();
    }

}
