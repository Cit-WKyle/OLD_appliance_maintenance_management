package com.kyle.ie.auth.security.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyle.ie.auth.error.ErrorCode;
import com.kyle.ie.auth.error.ErrorResponse;
import com.kyle.ie.auth.exceptions.AuthMethodNotSupportedException;
import com.kyle.ie.auth.exceptions.JwtExpiredTokenException;

@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private static final String INVALID_DETAILS = "Invalid username or password";
	private static final String EXPIRED_TOKEN = "Token has expired";
	private static final String AUTH_FAIL = "Authentication failed";

	private final ObjectMapper _mapper;
	
	@Autowired
	public AjaxAwareAuthenticationFailureHandler(ObjectMapper mapper) {
		_mapper = mapper;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		if (e instanceof BadCredentialsException) {
			_mapper.writeValue(response.getWriter(), ErrorResponse.of(INVALID_DETAILS, ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof JwtExpiredTokenException) {
			_mapper.writeValue(response.getWriter(), ErrorResponse.of(EXPIRED_TOKEN, ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof AuthMethodNotSupportedException) {
		    _mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		}

		_mapper.writeValue(response.getWriter(), ErrorResponse.of(AUTH_FAIL, ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
	}

}
