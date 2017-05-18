package com.kyle.ie.tenant.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.tenant.models.PropertyTenant;
import com.kyle.ie.tenant.repositories.IPropertyTenantRepository;

@Service
public class PropertyTenantService implements IPropertyTenantService {
	
	@Autowired
	private IPropertyTenantRepository _propRepo;

	@Override
	public List<PropertyTenant> getTenantsForProperty(String propertyId) {
		return _propRepo.findByPropertyId(propertyId);
	}

	@Override
	public boolean isTenantInProperty(int tennantId) {
		PropertyTenant tnt = _propRepo.findByAccountId(tennantId);
		return tnt != null;
	}

}
