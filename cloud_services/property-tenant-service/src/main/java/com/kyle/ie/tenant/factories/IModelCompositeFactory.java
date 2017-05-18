package com.kyle.ie.tenant.factories;

import com.kyle.ie.tenant.models.PendingTenant;
import com.kyle.ie.tenant.models.PendingTenantComposite;
import com.kyle.ie.tenant.models.PropertyTenant;
import com.kyle.ie.tenant.models.PropertyTenantComposite;

public interface IModelCompositeFactory {

	PendingTenantComposite compose(PendingTenant tenant, String username, String propertyName);
	
	PropertyTenantComposite compose(PropertyTenant tenant, String username, String email, String propertyName);
}
