package com.kyle.ie.appliance_status.clients.http;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kyle.ie.appliance_status.models.Status;


@FeignClient(value="appliance-status-service", name="appliance-status-service")
public interface IApplianceStatusClient {
	
	@RequestMapping(method=RequestMethod.POST, value="/ids")
	public List<Status> getApplainceStatusesForIds(@RequestBody List<String> ids);

}
