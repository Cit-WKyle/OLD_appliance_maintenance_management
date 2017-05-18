package com.kyle.ie.report.diagnostic.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyle.ie.property_appliance.models.PropertyApplianceComposite;

@FeignClient(value="property-appliance-service", name="property-appliance-service")
public interface IPropertyApplianceClient {
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public PropertyApplianceComposite getPropertyAppliance(@RequestParam("token") String token, @PathVariable("id") String id);
}
