package com.kyle.ie.auth.security.jwt.verifier;

public interface ITokenVerifier {
    public boolean verify(String jti);
}
