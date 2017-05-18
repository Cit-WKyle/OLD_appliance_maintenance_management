package com.kyle.ie.property_appliance.services;

import java.util.List;

import com.kyle.ie.property_appliance.models.PropertyAppliance;
import com.kyle.ie.property_appliance.models.PropertyApplianceComposite;

public interface IPropertyApplianceCompositeService {

	List<PropertyApplianceComposite> getPropertyAppliancesForProperty(String id);
	
	PropertyApplianceComposite generatePropertyApplianceComposite(PropertyAppliance propAppl);
}
