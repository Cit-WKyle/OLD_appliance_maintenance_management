package com.kyle.ie.property_appliance.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kyle.ie.property_appliance.form.PropertyApplianceForm;

@Component
public class PropertyApplianceFormValidator implements IPropertyApplianceFormValidator {

	private final static String INVALID_AGE_ERR = "Age must be between 1 & 100.";
	private List<String> _errors;
	
	public PropertyApplianceFormValidator() {
		_errors = new ArrayList<String>();
	}
	
	@Override
	public boolean validate(PropertyApplianceForm form) {
		boolean validApplId = validateApplId(form.getApplianceId());
		boolean validPropId = validatePropId(form.getPropertyId());
		boolean validName = validateName(form.getName());
		boolean validAge = validateAge(form.getAge()); 
		
		return validApplId && validPropId && validName && validAge;
	}

	@Override
	public List<String> getErrors() {
		return _errors;
	}

	private boolean validateAge(int age) {
		if(age > 0 || age < 100) {
			return true;
		} else {
			_errors.add(INVALID_AGE_ERR);
			return false;
		}
	}
	
	private boolean validateName(String name) {
		return true;
	}
	
	private boolean validateApplId(String id) {
		return true;
	}
	
	private boolean validatePropId(String id) {
		return true;
	}
}
