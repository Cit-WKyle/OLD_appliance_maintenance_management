package com.kyle.ie.property.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kyle.ie.property.form.PropertyForm;
import com.kyle.ie.property.models.Address;

@Component
public class PropertyFormValidator implements IPropertyFormValidator {

	private static final String AGE_ERR = "Property age must be between 1 & 200.";
	private static final String BEDCOUNT_ERR = "Bed count must be over 0.";
	private static final String BATHCNT_ERR = "Bathroom count must be over 0.";
	private static final String INVALID_MANAGER_ERR = "Invalid manager id provided.";
	
	private List<String> _errors;
	
	public PropertyFormValidator() {
		_errors = new ArrayList<>();
	}
	
	@Override
	public boolean validate(PropertyForm form) {
		boolean validAge = validateAge(form.getAge());
		boolean validBedCnt = validateBedCount(form.getBedCount());
		boolean validBathCnt = validateBathroomCount(form.getBathroomCount());
		boolean validMngId = validateManagerId(form.getManagerId());
		boolean validAddr = validateAddress(form.getAddress());
		
		return validAge && validBedCnt && validBathCnt && validMngId && validAddr;
	}

	@Override
	public List<String> getErrors() {
		return _errors;
	}

	private boolean validateAge(int age) {
		if(age > 0 || age < 200) {
			return true;
		} else {
			_errors.add(AGE_ERR);
			return false;
		}
	}
	
	private boolean validateBedCount(int bedCount) {
		if(bedCount > 0) {
			return true;
		} else {
			_errors.add(BEDCOUNT_ERR);
			return false;
		}
	}
	
	private boolean validateBathroomCount(int bathCount) {
		if(bathCount > 0) {
			return true;
		} else {
			_errors.add(BATHCNT_ERR);
			return false;
		}
	}
	
	private boolean validateManagerId(int managerId) {
		if(managerId > 0) {
			return true;
		} else {
			_errors.add(INVALID_MANAGER_ERR);
			return false;
		}
	}
	
	private boolean validateAddress(Address addr) {
		//TODO: address validate
		return true;
	}
}
