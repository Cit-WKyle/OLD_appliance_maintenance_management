package com.kyle.ie.property_appliance.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.property_appliance.clients.http.IAuthClient;
import com.kyle.ie.property_appliance.exceptions.AuthException;
import com.kyle.ie.property_appliance.form.PropertyApplianceForm;
import com.kyle.ie.property_appliance.json.JSONErrorGenerator;
import com.kyle.ie.property_appliance.json.PropertyApplianceCompositeJSONGenerator;
import com.kyle.ie.property_appliance.models.PropertyAppliance;
import com.kyle.ie.property_appliance.models.PropertyApplianceComposite;
import com.kyle.ie.property_appliance.models.StatusUpdatePayload;
import com.kyle.ie.property_appliance.services.IPropertyApplianceCompositeService;
import com.kyle.ie.property_appliance.services.IPropertyApplianceService;
import com.kyle.ie.property_appliance.validator.IPropertyApplianceFormValidator;

@RestController
@RequestMapping("/")
public class PropertyApplianceRestController {
	
	private static final String AUTH_FAILED = "Authentication failed";
	
	private static final String BEARER = "Bearer ";

	@Autowired
	private IAuthClient _authClient;
	
	@Autowired
	private IPropertyApplianceCompositeService _pApplCompService;
	
	@Autowired
	private IPropertyApplianceFormValidator _formValidator;
	
	@Autowired
	private IPropertyApplianceService _pApplService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public PropertyApplianceComposite getPropertyAppliance(@RequestParam String token, @PathVariable("id") String id) throws AuthException {
		
		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			return _pApplCompService.generatePropertyApplianceComposite(_pApplService.getForId(id));
		}
		
		throw new AuthException(AUTH_FAILED);
	}


	@RequestMapping(value = "/property/{id}", method=RequestMethod.GET)
	public List<PropertyApplianceComposite> getPropertyAppliancesForProperty(@RequestParam String token, @PathVariable("id") String id) throws AuthException {
		
		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			return _pApplCompService.getPropertyAppliancesForProperty(id);
		}
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String createPropertyAppliance(@RequestParam String token, @RequestBody PropertyApplianceForm form) throws AuthException {
		
		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			boolean valid = _formValidator.validate(form);
			if(!valid) {
				return new JSONErrorGenerator().generate(_formValidator.getErrors()).toString();
			}
			PropertyAppliance propAppl = _pApplService.save(form);
			PropertyApplianceComposite pApplComp = _pApplCompService.generatePropertyApplianceComposite(propAppl);
			return new PropertyApplianceCompositeJSONGenerator().generate(pApplComp).toString();
		}
		
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/status", method=RequestMethod.POST)
	public @ResponseBody PropertyApplianceComposite updatePropertyApplianceStatus(@RequestParam String token, @RequestBody StatusUpdatePayload pload) throws AuthException {
		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			PropertyAppliance pApp = _pApplService.getForId(pload.getPropertyApplianceId());
			_pApplService.updateStatus(pApp, pload.getStatusId());
			return _pApplCompService.generatePropertyApplianceComposite(pApp);
			
		}
		
		throw new AuthException(AUTH_FAILED);
	}
}
