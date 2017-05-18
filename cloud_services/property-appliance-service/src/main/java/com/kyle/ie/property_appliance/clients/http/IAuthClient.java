package com.kyle.ie.property_appliance.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.property_appliance.clients.http.AuthClientFallback;

@FeignClient(value="account-service", name="account-service", fallback= AuthClientFallback.class)
public interface IAuthClient {

	@RequestMapping(method = RequestMethod.GET, value = "/user/id")
	public AuthUserPayload auth(@RequestHeader("X-Authorization") String token);
}
