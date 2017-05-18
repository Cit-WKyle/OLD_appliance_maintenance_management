package com.kyle.ie.tenant.services;

import java.util.List;

import com.kyle.ie.tenant.models.ApplyToPropertyPayload;
import com.kyle.ie.tenant.models.PendingTenant;
import com.kyle.ie.tenant.models.PendingTenantUpdatePayload;
import com.kyle.ie.tenant.models.PropertyTenant;

public interface IPendingTenantService {

	PendingTenant applyToProperty(ApplyToPropertyPayload payload);
	
	void updatePendingTenant(PendingTenantUpdatePayload payload);
	
	List<PendingTenant> getPendingTenantsForPropertyList(List<String> propertyIds);
	
	PropertyTenant confirmAccepted(String pendingTenantId);
}
