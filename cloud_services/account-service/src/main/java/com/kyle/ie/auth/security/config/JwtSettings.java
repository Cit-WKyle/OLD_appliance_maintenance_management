package com.kyle.ie.auth.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth.security.jwt")
public class JwtSettings {

	private Integer _tokenExpirationTime;
	
	private String _tokenIssuer;
	
	private String _tokenSigningKey;
	
	private Integer _refreshTokenExpTime;
	
	public Integer getTokenExpirationTime() {
		return _tokenExpirationTime;
	}
	
	public void setTokenExpirationTime(Integer tokenExpirationTime) {
		_tokenExpirationTime = tokenExpirationTime;
	}
	
	public String getTokenIssuer() {
		return _tokenIssuer;
	}
	
	public void setTokenIssuer(String tokenIssuer) {
		_tokenIssuer = tokenIssuer;
	}
	
	public String getTokenSigningKey() {
		return _tokenSigningKey;
	}
	
	public void setTokenSigningKey(String tokenSigningKey) {
		_tokenSigningKey = tokenSigningKey;
	}
	
	public Integer getRefreshTokenExpTime() {
		return _refreshTokenExpTime;
	}
	
	public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
		_refreshTokenExpTime = refreshTokenExpTime;
	}
}
