package com.kyle.ie.appliance_status.services;

import java.util.List;

import com.kyle.ie.appliance_status.model.Status;
import com.kyle.ie.appliance_status.model.constants.ApplianceType;
import com.kyle.ie.appliance_status.model.constants.StatusType;

public interface IApplianceStatusService {

	Status getStatusForId(String id);
	
	List<Status> getStatusesForIds(List<String> ids);
	
	List<Status> getStatusesForApplianceType(ApplianceType type);
	
	String getCommonStatusId(StatusType type);
}
