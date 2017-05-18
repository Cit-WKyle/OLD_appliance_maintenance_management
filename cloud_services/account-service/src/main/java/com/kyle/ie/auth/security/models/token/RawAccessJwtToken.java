package com.kyle.ie.auth.security.models.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;

import com.kyle.ie.auth.exceptions.JwtExpiredTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class RawAccessJwtToken implements IJwtToken {
	
	private static final String INVALID_JWT = "Invalid JWT Token: ";
	private static final String JWT_EXPIRED = "JWT Token expired";
	
    private static Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);
    
    private String _token;
    
    public RawAccessJwtToken(String token) {
    	_token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(_token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            logger.error(INVALID_JWT, ex);
            throw new BadCredentialsException(INVALID_JWT, ex);
        } catch (ExpiredJwtException expiredEx) {
            logger.info(JWT_EXPIRED, expiredEx);
            throw new JwtExpiredTokenException(this, JWT_EXPIRED, expiredEx);
        }
    }
    
    @Override
    public String getToken() {
        return _token;
    }
}
