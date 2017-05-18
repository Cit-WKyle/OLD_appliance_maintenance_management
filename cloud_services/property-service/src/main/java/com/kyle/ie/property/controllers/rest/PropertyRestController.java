package com.kyle.ie.property.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.property.clients.http.IAuthClient;
import com.kyle.ie.property.exceptions.AuthException;
import com.kyle.ie.property.form.PropertyForm;
import com.kyle.ie.property.json.JSONErrorGenerator;
import com.kyle.ie.property.json.JSONPropertyGenerator;
import com.kyle.ie.property.models.Property;
import com.kyle.ie.property.services.IPropertyService;
import com.kyle.ie.property.validator.IPropertyFormValidator;

@RestController
@RequestMapping(value="/")
public class PropertyRestController {
	
	private static final String BEARER = "Bearer ";

	@Autowired
	private IPropertyService _propService;
	
	@Autowired
	private IAuthClient _authClient;
	
	@Autowired
	private IPropertyFormValidator _propFormVal;
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public List<Property> getPropertiesForManager(@RequestParam String token) throws AuthException {
		AuthUserPayload payload = _authClient.auth(BEARER + token);
		if(payload.getSuccess()) {
			int id = (int) payload.getId();
			return _propService.getPropertiesForManager(id);
		} 
		throw new AuthException(payload.getError());
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST,  produces={ MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String createProperty(@RequestParam String token, @RequestBody PropertyForm form) throws AuthException {
		
		AuthUserPayload payload = _authClient.auth(BEARER + token); 
		if(payload.getSuccess()) {
			boolean validForm = _propFormVal.validate(form);
			if(!validForm) {
				return new JSONErrorGenerator().generate(_propFormVal.getErrors()).toString();
			}
			
			return new JSONPropertyGenerator().generate(_propService.save(form)).toString();
		}
		throw new AuthException(payload.getError());
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Property getProperty(@RequestParam String token, @PathVariable("id") String id) throws AuthException {
		AuthUserPayload payload = _authClient.auth(BEARER + token); 
		if(payload.getSuccess()) {
			return _propService.get(id);
		}
		throw new AuthException(payload.getError());
	}

}