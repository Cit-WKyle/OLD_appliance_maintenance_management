package com.kyle.ie.tenant.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.tenant.models.ApplyToPropertyPayload;
import com.kyle.ie.tenant.models.PendingTenant;
import com.kyle.ie.tenant.models.PendingTenantUpdatePayload;
import com.kyle.ie.tenant.models.PropertyTenant;
import com.kyle.ie.tenant.models.constants.PendingStatus;
import com.kyle.ie.tenant.repositories.IPendingTenantRepository;
import com.kyle.ie.tenant.repositories.IPropertyTenantRepository;

@Service
public class PendingTenantService implements IPendingTenantService {

	@Autowired
	private IPendingTenantRepository _pendRepo;
	
	@Autowired
	private IPropertyTenantRepository _propRepo;
	
	@Override
	public PendingTenant applyToProperty(ApplyToPropertyPayload payload) {
		PendingTenant pendTnt = new PendingTenant();
		pendTnt.setAccountId(payload.getAccountId());
		pendTnt.setPropertyId(payload.getPropertyId());
		pendTnt.setStatus(PendingStatus.PENDING);
		
		_pendRepo.save(pendTnt);
		return pendTnt;
	}

	@Override
	public void updatePendingTenant(PendingTenantUpdatePayload payload) {
		PendingTenant pdTnt = _pendRepo.findOne(payload.getPendingId());
		pdTnt.setStatus(PendingStatus.valueOf(payload.getStatus()));
		_pendRepo.save(pdTnt);
	}

	@Override
	public List<PendingTenant> getPendingTenantsForPropertyList(List<String> propertyIds) {
		List<PendingTenant> pendTnt = new ArrayList<>();
		
		for(String id: propertyIds) {
			pendTnt.add(_pendRepo.findByPropertyId(id));
		}
		
		return pendTnt;
	}

	@Override
	public PropertyTenant confirmAccepted(String pendingTenantId) {
		PendingTenant tnt = _pendRepo.findOne(pendingTenantId);
		
		PropertyTenant propTnt = new PropertyTenant();
		propTnt.setAccountId(tnt.getAccountId());
		propTnt.setPropertyId(tnt.getPropertyId());
		
		_propRepo.save(propTnt);
		_pendRepo.delete(pendingTenantId);
		
		return propTnt;
	}
}
