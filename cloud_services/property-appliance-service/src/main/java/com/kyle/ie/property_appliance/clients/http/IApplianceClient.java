package com.kyle.ie.property_appliance.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kyle.ie.appliance.models.Appliance;

@FeignClient(value="appliance-service", name="appliance-service", fallback= ApplianceClientFallback.class)
public interface IApplianceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	Appliance getAppliance(@PathVariable("id") String id);
}
