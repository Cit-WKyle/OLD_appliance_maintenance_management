package com.kyle.ie.tenant.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kyle.ie.property.models.Property;

@FeignClient(value="property-service", name="property-service")
public interface IPropertyClient {

	Property getProperty(@PathVariable("id") String id, @RequestBody String token);
}
