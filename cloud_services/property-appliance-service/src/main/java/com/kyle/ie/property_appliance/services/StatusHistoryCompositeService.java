package com.kyle.ie.property_appliance.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.appliance_status.models.ApplianceStatus;
import com.kyle.ie.property_appliance.clients.http.IApplianceStatusClient;
import com.kyle.ie.property_appliance.models.StatusHistory;
import com.kyle.ie.property_appliance.models.StatusHistoryComposite;

@Service
public class StatusHistoryCompositeService implements IStatusHistoryCompositeService {
	
	@Autowired
	private IApplianceStatusClient _applStatClient;

	@Override
	public List<StatusHistoryComposite> composeStatusHistoryList(List<StatusHistory> statusHistory) {
		
		Set<String> ids = new HashSet<>();
		
		List<StatusHistoryComposite> statusHistoryComp = new ArrayList<>();
		
		for(StatusHistory statHist: statusHistory) {
			ids.add(statHist.getStatusId());
		}
		
		List<ApplianceStatus> applStatuses;
		if(ids.size() > 0) {
			applStatuses = _applStatClient.getApplainceStatusesForIds(new ArrayList<>(ids));
		} else {
			applStatuses = new ArrayList<>();
		}
		
		for(StatusHistory statHist: statusHistory) {
			StatusHistoryComposite statHistComp = new StatusHistoryComposite(statHist);
			for(ApplianceStatus applStat: applStatuses) {
				if(applStat.getId().equals(statHist.getStatusId())) {
					statHistComp.setStatus(applStat);
				}
			}
			statusHistoryComp.add(statHistComp);
		}
		
		return statusHistoryComp;
	}

}
