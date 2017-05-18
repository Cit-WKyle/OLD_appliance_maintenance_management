package com.kyle.ie.auth.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kyle.ie.auth.forms.RegisterForm;
import com.kyle.ie.auth.repositories.IUserRepository;

@Component
public class RegistrationValidator implements IRegistrationValidator {

	private static final String UNIQUE_USERNAME_ERR = "Username is not available.";
	private static final String USERNAME_LENGTH_ERR = "Username must be between 4 & 8 characters.";
	
	private static final String PASSWORD_LENGTH_ERR = "Password must be between 4 & 8 characters.";
	private static final String PWD_PWDCONF_ERR = "Password & Password Confirmation must be equal.";
	
	private static final String EMAIL_UNIQUE_ERR = "Email is already being used.";
	private static final String INVALID_EMAIL_ERR = "Provided email is invalid";
	
	private static final String INVALID_USERTYPE = "Invalid user type provided.";
	
	private static final String EMAIL_REGEX = "";
	
	@Autowired
	private IUserRepository _repo;
	
	private List<String> _errors;
	
	public RegistrationValidator() {
		_errors = new ArrayList<>();
	}
	
	@Override
	public boolean validate(RegisterForm form) {
		boolean usernameValid = validateUsername(form.getUsername());
		boolean passwordValid = validatePassword(form.getPassword(), form.getPasswordConfirm());
		boolean emailValid = validateEmail(form.getEmail());
		boolean usertypeValid = validateUserType(form.getUserType());
		
		return usernameValid && passwordValid && emailValid && usertypeValid;
	}

	@Override
	public List<String> getErrors() {
		return _errors;
	}
	
	private boolean validateUsername(String username) {
		if(_repo.isUsernameUsed(username) < 1) {
			if(username.length() > 3 && username.length() < 9) {
				return true;
			} else {
				_errors.add(USERNAME_LENGTH_ERR);
				return false;
			}
		} else {
			_errors.add(UNIQUE_USERNAME_ERR);
			return false;
		}
	}

	private boolean validatePassword(String password, String passwordConfirm) {
		if(password.length() > 3 && password.length() < 9) {
			if(password.equals(passwordConfirm)) {
				return true;
			} else {
				_errors.add(PWD_PWDCONF_ERR);
				return false;
			}
		} else {
			_errors.add(PASSWORD_LENGTH_ERR);
			return false;
		}
	}
	
	private boolean validateEmail(String email) {
		if(email.matches(EMAIL_REGEX)) {
			if(_repo.isEmailUsed(email) < 1) {
				return true;
			} else {
				_errors.add(EMAIL_UNIQUE_ERR);
				return false;
			}
		} else {
			_errors.add(INVALID_EMAIL_ERR);
			return false;
		}
	}
	
	private boolean validateUserType(String userType) {
		boolean valid = userType.equals("property_manager") || userType.equals("tenant") || userType.equals("maintenance_engineer");
		if(!valid) {
			_errors.add(INVALID_USERTYPE);
		}
		return valid;
	}
}
