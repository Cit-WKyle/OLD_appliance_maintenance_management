package com.kyle.ie.appliance_status.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.appliance_status.clients.http.IApplianceStatusClient;
import com.kyle.ie.appliance_status.models.PropertyApplianceStatusUpdatePayload;
import com.kyle.ie.appliance_status.models.Status;
import com.kyle.ie.appliance_status.models.StatusNotificationPayload;

@Service
public class StatusNotificationService implements IStatusNotificationService {
	
	@Autowired
	private IApplianceStatusClient _statClient;

	@Override
	public StatusNotificationPayload prepareNotification(PropertyApplianceStatusUpdatePayload pAppStatPload) {
		StatusNotificationPayload statNotPload = new StatusNotificationPayload();
		statNotPload.setPropertyApplianceId(pAppStatPload.getPropertyApplianceId());
		statNotPload.setPropertyApplianceName(pAppStatPload.getPropertyApplianceName());
		
		String prevStatusId = pAppStatPload.getOldStatusId();
		String newStatusId = pAppStatPload.getNewStatusId();
		//TODO: If the status is the same (unlikely) just do single request
		List<String> ids = new ArrayList<String>();
		ids.add(prevStatusId);
		ids.add(newStatusId);
		
		List<Status> statuses = _statClient.getApplainceStatusesForIds(ids);
		
		for(Status stat: statuses) {
			if(stat.getId().equals(prevStatusId)) {
				statNotPload.setPreviousStatus(stat);
			} else if(stat.getId().equals(newStatusId)) {
				statNotPload.setNewStatus(stat);
			}
		}
		
		return statNotPload;
	}

}
