package com.kyle.ie.auth.security.models.token;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kyle.ie.auth.security.config.JwtSettings;
import com.kyle.ie.auth.security.models.Scopes;
import com.kyle.ie.auth.security.models.UserContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenFactory {
	private final JwtSettings _settings;
	
	private static final String CANNOT_CREATE_JWT_USERNAME = "Cannot create JWT Token without username";
	private static final String USER_NO_PRIVILEGES = "User doesn't have any privileges";
	private static final String SCOPES = "scopes";
	
	@Autowired
	public JwtTokenFactory(JwtSettings settings) {
		_settings = settings;
	}
	
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) 
            throw new IllegalArgumentException(CANNOT_CREATE_JWT_USERNAME);

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) 
            throw new IllegalArgumentException(USER_NO_PRIVILEGES);

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put(SCOPES, userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        DateTime currentTime = new DateTime();

        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(_settings.getTokenIssuer())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(_settings.getTokenExpirationTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, _settings.getTokenSigningKey())
        .compact();

        return new AccessJwtToken(token, claims);
    }

    public IJwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            throw new IllegalArgumentException(CANNOT_CREATE_JWT_USERNAME);
        }

        DateTime currentTime = new DateTime();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put(SCOPES, Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        
        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(_settings.getTokenIssuer())
          .setId(UUID.randomUUID().toString())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(_settings.getRefreshTokenExpTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, _settings.getTokenSigningKey())
        .compact();

        return new AccessJwtToken(token, claims);
    }
}
