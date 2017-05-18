package com.kyle.ie.auth.validation;

import java.util.List;

import com.kyle.ie.auth.forms.RegisterForm;

public interface IRegistrationValidator {

	boolean validate(RegisterForm form);
	
	List<String> getErrors();
}
