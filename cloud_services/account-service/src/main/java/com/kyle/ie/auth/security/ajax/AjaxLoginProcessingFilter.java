package com.kyle.ie.auth.security.ajax;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyle.ie.auth.exceptions.AuthMethodNotSupportedException;
import com.kyle.ie.auth.util.WebUtil;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	private static final String LOG_AUTH_NOT_SUPP = "Authentication method not supported. Request method: ";
	private static final String AUTH_NOT_SUPP = "Authentication method not supported.";
	private static final String USER_NOT_PROV = "Username or Password not provided";

	private static Logger logger = LoggerFactory.getLogger(AjaxLoginProcessingFilter.class);
	
	private final AuthenticationSuccessHandler _successHandler;
	private final AuthenticationFailureHandler _failureHandler;
	private final ObjectMapper _objectMapper;
	
    public AjaxLoginProcessingFilter(String defaultFilterProcessesUrl, AuthenticationSuccessHandler successHandler, 
            AuthenticationFailureHandler failureHandler, ObjectMapper mapper) {
		super(defaultFilterProcessesUrl);
		_successHandler = successHandler;
		_failureHandler = failureHandler;
		_objectMapper = mapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod()) || !WebUtil.isAjax(request)) {
            if(logger.isDebugEnabled()) {
                logger.debug(LOG_AUTH_NOT_SUPP + request.getMethod());
            }
            throw new AuthMethodNotSupportedException(AUTH_NOT_SUPP);
        }

        LoginRequest loginRequest = _objectMapper.readValue(request.getReader(), LoginRequest.class);
        
        if (StringUtils.isBlank(loginRequest.getUsername()) || StringUtils.isBlank(loginRequest.getPassword())) {
            throw new AuthenticationServiceException(USER_NOT_PROV);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        return this.getAuthenticationManager().authenticate(token);
	}

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        _successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        _failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
