package com.kyle.ie.appliance_status.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.appliance_status.model.Status;
import com.kyle.ie.appliance_status.model.constants.ApplianceType;
import com.kyle.ie.appliance_status.model.constants.StatusType;
import com.kyle.ie.appliance_status.services.IApplianceStatusService;

@RestController
@RequestMapping("/")
public class ApplianceStatusController {

	@Autowired
	private IApplianceStatusService _applStatService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Status getStatusForId(@PathVariable("id") String id) {
		return _applStatService.getStatusForId(id);
	}
	
	@RequestMapping(value="/ids", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public List<Status> getStatusesForIds(@RequestBody List<String> statusIds) {
		return _applStatService.getStatusesForIds(statusIds);
	}
	
	@RequestMapping(value="/appliance-type/{type}", method=RequestMethod.GET)
	public List<Status> getStatusesForApplType(@PathVariable("type") String type) {
		return _applStatService.getStatusesForApplianceType(ApplianceType.valueOf(type));
	}
	
	@RequestMapping(value="/type/id/{type}", method=RequestMethod.GET) 
	public String getIdForType(@PathVariable("type") String type) {
		return _applStatService.getCommonStatusId(StatusType.valueOf(type));
	}
}
