package com.kyle.ie.property_appliance.services;

import java.util.List;

import com.kyle.ie.property_appliance.form.PropertyApplianceForm;
import com.kyle.ie.property_appliance.models.PropertyAppliance;

public interface IPropertyApplianceService {

	List<PropertyAppliance> getPropertyAppliancesForProperty(String id);
	
	PropertyAppliance save(PropertyApplianceForm form);
	
	PropertyAppliance getForId(String id);
	
	void update(PropertyAppliance pAppl);
	
	void updateStatus(PropertyAppliance pAppl, String newStatusId);
}
