package com.kyle.ie.property.validator;

import java.util.List;

import com.kyle.ie.property.form.PropertyForm;

public interface IPropertyFormValidator {

	boolean validate(PropertyForm form);
	
	List<String> getErrors();
}
