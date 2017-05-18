package com.kyle.ie.tenant.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.auth.models.UserDetails;

@FeignClient(value="account-service", name="account-service", fallback= AuthClientFallback.class)
public interface IAuthClient {

	@RequestMapping(method = RequestMethod.GET, value = "/user/id")
	public AuthUserPayload auth(@RequestHeader("X-Authorization") String token);
	

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public UserDetails user(@RequestHeader("X-Authorization") String token);
}

