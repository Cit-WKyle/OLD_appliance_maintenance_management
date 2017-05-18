package com.kyle.ie.tenant.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.auth.models.UserDetails;
import com.kyle.ie.property.models.Property;
import com.kyle.ie.tenant.clients.http.IAuthClient;
import com.kyle.ie.tenant.clients.http.IPropertyClient;
import com.kyle.ie.tenant.exception.AuthException;
import com.kyle.ie.tenant.factories.IModelCompositeFactory;
import com.kyle.ie.tenant.models.ApplyToPropertyPayload;
import com.kyle.ie.tenant.models.PendingTenant;
import com.kyle.ie.tenant.models.PendingTenantComposite;
import com.kyle.ie.tenant.models.PendingTenantUpdatePayload;
import com.kyle.ie.tenant.models.PropertyTenant;
import com.kyle.ie.tenant.models.PropertyTenantComposite;
import com.kyle.ie.tenant.services.IPendingTenantService;
import com.kyle.ie.tenant.services.IPropertyTenantService;

@RestController
@RequestMapping("/")
public class PropertyTennantRestController {
	
	private static final String AUTH_FAILED = "Authentication failed";
	
	private static final String MANAGER_TYPE = "property_manager";
	private static final String TENANT_TYPE = "tenant";
	
	private static final String BEARER = "Bearer ";
	
	@Autowired
	private IPropertyTenantService _propTntServ;
	
	@Autowired
	private IPendingTenantService _pendTntServ;

	@Autowired
	private IAuthClient _authClient;
	
	@Autowired
	private IPropertyClient _propClient;
	
	@Autowired
	private IModelCompositeFactory _compFactory;

	@RequestMapping(value = "/property-tenants/{id}", method=RequestMethod.GET)
	public List<PropertyTenantComposite> getTenantsForProperty(@PathVariable("id") String propertyId, @RequestParam String token) throws AuthException {
		
		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			if(apl.getType().equals(MANAGER_TYPE)) {
				List<PropertyTenant> tnts = _propTntServ.getTenantsForProperty(propertyId);
				List<PropertyTenantComposite> compos = new ArrayList<>();
				for(PropertyTenant tnt: tnts) {
					compos.add(aggregateForComposite(tnt, token));
				}
				return compos;
			}
		}
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/pending-tenants", method=RequestMethod.GET)
	public List<PendingTenantComposite> getPendingTenantsForProperties(@RequestParam(value="token") String token, @RequestParam(value="ids[]") List<String> ids) throws AuthException {
		
		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			if(apl.getType().equals(MANAGER_TYPE)) {
				List<PendingTenant> tnts = _pendTntServ.getPendingTenantsForPropertyList(ids);
				List<PendingTenantComposite> compos = new ArrayList<>();
				for(PendingTenant tnt: tnts) {
					compos.add(aggregateForComposite(tnt, token));
				}
				return compos;
			}
		}
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/tenant/apply", method=RequestMethod.POST)
	public PendingTenantComposite applyToProperty(@RequestParam String token, @RequestBody ApplyToPropertyPayload payload) throws AuthException {
		
		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			if(apl.getType().equals(TENANT_TYPE) && !_propTntServ.isTenantInProperty(payload.getAccountId())) {
				PendingTenant tnt = _pendTntServ.applyToProperty(payload);
				return aggregateForComposite(tnt, token);
				
			}
		}
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/manager/update", method=RequestMethod.POST)
	public void updatePendingTenant(@RequestParam String token, @RequestBody PendingTenantUpdatePayload payload) throws AuthException {
		
		AuthUserPayload apl = _authClient.auth(BEARER + token);
		if(apl.getSuccess()) {
			if(apl.getType().equals(MANAGER_TYPE)) {
				_pendTntServ.updatePendingTenant(payload);
				return;
			}
		}
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/tenant/confirm", method=RequestMethod.POST)
	public PropertyTenantComposite confirmAccepted(@RequestParam(value="token") String token, @RequestParam(value="id") String id) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		if(apl.getSuccess()) {
			if(apl.getSuccess()) {
				if(apl.getType().equals(TENANT_TYPE)) {
					PropertyTenant tnt = _pendTntServ.confirmAccepted(id);
					return aggregateForComposite(tnt, token);
				}
			}
		}
		throw new AuthException(AUTH_FAILED);
	}
	
	private PendingTenantComposite aggregateForComposite(PendingTenant tnt, String token) {
		Property prop = _propClient.getProperty(tnt.getPropertyId(), token);
		StringBuilder builder = new StringBuilder();
		builder.append(prop.getAddress().getAddressLine1());
		builder.append(",");
		builder.append(prop.getAddress().getAddressLine2()); 
		return _compFactory.compose(tnt, _authClient.user(token).getUsername(), builder.toString());
	}
	
	private PropertyTenantComposite aggregateForComposite(PropertyTenant tnt, String token) {
		Property prop = _propClient.getProperty(tnt.getPropertyId(), token);
		StringBuilder builder = new StringBuilder();
		builder.append(prop.getAddress().getAddressLine1());
		builder.append(",");
		builder.append(prop.getAddress().getAddressLine2()); 
		UserDetails user = _authClient.user(token);
		return _compFactory.compose(tnt, user.getUsername(), user.getEmail(), builder.toString());
		
	}
}
