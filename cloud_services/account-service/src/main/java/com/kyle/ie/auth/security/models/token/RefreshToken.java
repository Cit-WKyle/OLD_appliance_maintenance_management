package com.kyle.ie.auth.security.models.token;

import java.util.List;
import java.util.Optional;

import com.kyle.ie.auth.security.models.Scopes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@SuppressWarnings("unchecked")
public class RefreshToken implements IJwtToken {
    private Jws<Claims> _claims;
    
    private RefreshToken(Jws<Claims> claims) {
    	_claims = claims;
    }

    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
    	final String SCOPES = "scopes";
    	
    	Jws<Claims> claims = token.parseClaims(signingKey);
    	
    	List<String> scopes = claims.getBody().get(SCOPES, List.class);        
    	if (scopes == null || scopes.isEmpty() 
                || !scopes.stream().filter(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope)).findFirst().isPresent()) {
            return Optional.empty();
        }
        return Optional.of(new RefreshToken(claims));
    }
    
	@Override
	public String getToken() {
		return null;
	}
	
	public Jws<Claims> getClaims() {
		return _claims;
	}
	
	public String getJti() {
		return _claims.getBody().getId();
	}
	
	public String getSubject() {
		return _claims.getBody().getSubject();
	}

}
