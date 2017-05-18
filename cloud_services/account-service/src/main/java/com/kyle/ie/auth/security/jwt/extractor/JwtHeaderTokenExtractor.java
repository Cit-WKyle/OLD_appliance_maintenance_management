package com.kyle.ie.auth.security.jwt.extractor;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements ITokenExtractor {
	
	private static final String AUTH_HEADER_BLANK_ERR = "Authorization header cannot be blank!";
	private static final String INV_AUTH_HEADER_SZ = "Invalid authorization header size.";
	
    public static String HEADER_PREFIX = "Bearer ";

	@Override
	public String extract(String payload) {
        if (StringUtils.isBlank(payload)) {
            throw new AuthenticationServiceException(AUTH_HEADER_BLANK_ERR);
        }
        if (payload.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException(INV_AUTH_HEADER_SZ);
        }
        return payload.substring(HEADER_PREFIX.length(), payload.length());
    }

}
