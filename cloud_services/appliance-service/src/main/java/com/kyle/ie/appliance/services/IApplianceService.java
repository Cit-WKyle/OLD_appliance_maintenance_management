package com.kyle.ie.appliance.services;

import java.util.List;

import com.kyle.ie.appliance.models.Appliance;
import com.kyle.ie.appliance.models.constants.ApplianceType;

public interface IApplianceService {

	List<Appliance> getAppliances();
	
	List<Appliance> getAppliancesByType(ApplianceType type);
	
	Appliance getApplianceForId(String id);
}
