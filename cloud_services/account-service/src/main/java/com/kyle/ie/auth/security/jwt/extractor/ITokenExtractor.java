package com.kyle.ie.auth.security.jwt.extractor;

public interface ITokenExtractor {
    public String extract(String payload);
}
