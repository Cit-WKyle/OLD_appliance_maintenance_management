package com.kyle.ie.auth.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kyle.ie.auth.models.User;

@Component
public class LoginValidator implements ILoginValidator {
	
	@Autowired
	private BCryptPasswordEncoder _encoder;

	public boolean validate(User user, String password) {
		return _encoder.matches(password, user.getPassword());
	}
}
