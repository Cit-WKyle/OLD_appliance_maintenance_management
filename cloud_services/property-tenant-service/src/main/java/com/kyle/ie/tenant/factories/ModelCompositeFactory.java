package com.kyle.ie.tenant.factories;

import org.springframework.stereotype.Component;

import com.kyle.ie.tenant.models.PendingTenant;
import com.kyle.ie.tenant.models.PendingTenantComposite;
import com.kyle.ie.tenant.models.PropertyTenant;
import com.kyle.ie.tenant.models.PropertyTenantComposite;

@Component
public class ModelCompositeFactory implements IModelCompositeFactory {

	@Override
	public PendingTenantComposite compose(PendingTenant tenant, String username, String propertyName) {
		PendingTenantComposite comp = new PendingTenantComposite();
		comp.setTenant(tenant);
		comp.setUsername(username);
		comp.setPropertyName(propertyName);
		return comp;
	}

	@Override
	public PropertyTenantComposite compose(PropertyTenant tenant, String username, String email, String propertyName) {
		PropertyTenantComposite comp = new PropertyTenantComposite();
		comp.setTenant(tenant);
		comp.setUsername(username);
		comp.setEmail(email);
		comp.setPropertyName(propertyName);
		return comp;
	}

}
