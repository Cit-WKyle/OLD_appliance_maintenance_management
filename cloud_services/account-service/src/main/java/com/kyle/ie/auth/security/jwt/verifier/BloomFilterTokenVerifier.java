package com.kyle.ie.auth.security.jwt.verifier;

import org.springframework.stereotype.Component;

@Component
public class BloomFilterTokenVerifier implements ITokenVerifier {

	@Override
	public boolean verify(String jti) {
        return true;
	}

}
