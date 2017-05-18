package com.kyle.ie.property_appliance.clients.http;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kyle.ie.appliance_status.models.ApplianceStatus;

@FeignClient(value="appliance-status-service", name="appliance-status-service")
public interface IApplianceStatusClient {

	@RequestMapping(method= RequestMethod.GET, value="/{id}")
	ApplianceStatus getApplianceStatus(@PathVariable("id") String id);
	
	@RequestMapping(method= RequestMethod.POST, value="/ids")
	public List<ApplianceStatus> getApplainceStatusesForIds(@RequestBody List<String> ids);
	
	@RequestMapping(value="/type/id/{type}", method=RequestMethod.GET) 
	public String getIdForType(@PathVariable("type") String type);
}
