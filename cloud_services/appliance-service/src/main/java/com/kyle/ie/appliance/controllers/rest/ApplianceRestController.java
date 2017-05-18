package com.kyle.ie.appliance.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.appliance.models.Appliance;
import com.kyle.ie.appliance.models.constants.ApplianceType;
import com.kyle.ie.appliance.services.IApplianceService;

@RestController
@RequestMapping(value="/")
public class ApplianceRestController {
	
	@Autowired
	private IApplianceService _applService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public Appliance getAppliance(@PathVariable("id") String id) {
		return _applService.getApplianceForId(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Appliance> getAppliances() {
		return _applService.getAppliances();
	}
	
	@RequestMapping(value = "/type/{type}", method=RequestMethod.GET)
	public List<Appliance> getAppliancesByType(@PathVariable("type") ApplianceType type) {
		return _applService.getAppliancesByType(type);
	}

}
