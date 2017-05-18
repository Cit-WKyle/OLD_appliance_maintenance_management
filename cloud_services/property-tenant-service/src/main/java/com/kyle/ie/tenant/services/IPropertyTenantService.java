package com.kyle.ie.tenant.services;

import java.util.List;

import com.kyle.ie.tenant.models.PropertyTenant;

public interface IPropertyTenantService {

	List<PropertyTenant> getTenantsForProperty(String propertyId);
	
	boolean isTenantInProperty(int tennantId);
}
