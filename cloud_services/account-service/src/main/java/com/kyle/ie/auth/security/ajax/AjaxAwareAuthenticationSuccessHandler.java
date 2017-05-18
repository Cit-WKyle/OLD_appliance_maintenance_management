package com.kyle.ie.auth.security.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyle.ie.auth.security.models.UserContext;
import com.kyle.ie.auth.security.models.token.IJwtToken;
import com.kyle.ie.auth.security.models.token.JwtTokenFactory;

@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private static final String TOKEN = "token";
	private static final String REF_TOKEN = "refreshToken";
	
	private final ObjectMapper _mapper;
	private final JwtTokenFactory _tokenFactory;
	
    @Autowired
    public AjaxAwareAuthenticationSuccessHandler(final ObjectMapper mapper, final JwtTokenFactory tokenFactory) {
        _mapper = mapper;
        _tokenFactory = tokenFactory;
    }

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
        UserContext userContext = (UserContext) auth.getPrincipal();
        
        IJwtToken accessToken = _tokenFactory.createAccessJwtToken(userContext);
        IJwtToken refreshToken = _tokenFactory.createRefreshToken(userContext);
        
        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put(TOKEN, accessToken.getToken());
        tokenMap.put(REF_TOKEN, refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        _mapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
	}
	
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
