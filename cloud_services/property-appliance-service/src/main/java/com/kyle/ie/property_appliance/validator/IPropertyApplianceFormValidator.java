package com.kyle.ie.property_appliance.validator;

import java.util.List;

import com.kyle.ie.property_appliance.form.PropertyApplianceForm;

public interface IPropertyApplianceFormValidator {

	boolean validate(PropertyApplianceForm form);
	
	List<String> getErrors();
}
