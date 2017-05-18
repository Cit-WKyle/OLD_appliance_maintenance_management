package com.kyle.ie.property_appliance.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.appliance_status.models.PropertyApplianceStatusUpdatePayload;
import com.kyle.ie.property_appliance.clients.http.IApplianceStatusClient;
import com.kyle.ie.property_appliance.clients.http.IStatusNotificationClient;
import com.kyle.ie.property_appliance.form.PropertyApplianceForm;
import com.kyle.ie.property_appliance.models.PropertyAppliance;
import com.kyle.ie.property_appliance.models.StatusHistory;
import com.kyle.ie.property_appliance.repositories.IPropertyApplianceRepository;

@Service
public class PropertyApplianceService implements IPropertyApplianceService {
	
	@Autowired
	private IPropertyApplianceRepository _pApplRepo;
	
	@Autowired
	private IApplianceStatusClient _applStatClient;
	
	@Autowired 
	private IStatusNotificationClient _statNotClient;
	
	private static final String OKAY_STAT = "OKAY";

	@Override
	public List<PropertyAppliance> getPropertyAppliancesForProperty(String id) {
		return _pApplRepo.findByPropertyId(id);
	}

	@Override
	public PropertyAppliance save(PropertyApplianceForm form) {
		PropertyAppliance propAppl = new PropertyAppliance();
		propAppl.setAge(form.getAge());
		propAppl.setApplianceId(form.getApplianceId());
		propAppl.setName(form.getName());
		propAppl.setPropertyId(form.getPropertyId());
		propAppl.setStatusHistory(new ArrayList<>());
		propAppl.setStatusId(_applStatClient.getIdForType(OKAY_STAT)); 
		_pApplRepo.save(propAppl);
		return propAppl;
	}

	@Override
	public PropertyAppliance getForId(String id) {
		return _pApplRepo.findOne(id);
	}

	@Override
	public void update(PropertyAppliance pAppl) {
		_pApplRepo.save(pAppl);
	}

	@Override
	public void updateStatus(PropertyAppliance pAppl, String newStatusId) {
		String oldStatusId = pAppl.getStatusId();
		StatusHistory statHist = new StatusHistory();
		statHist.setStatusId(oldStatusId);
		statHist.setDateTime((int) new DateTime().getMillis());

		pAppl.setStatusId(newStatusId);
		pAppl.getStatusHistory().add(statHist);
		_pApplRepo.save(pAppl);
		
		PropertyApplianceStatusUpdatePayload pload = new PropertyApplianceStatusUpdatePayload();
		pload.setNewStatusId(newStatusId);
		pload.setOldStatusId(oldStatusId);
		pload.setPropertyApplianceId(pAppl.getId());
		pload.setPropertyApplianceName(pAppl.getName());
		
		_statNotClient.sendNotification(pload);
	}

}
