package com.kyle.ie.auth.validation;

import com.kyle.ie.auth.models.User;

public interface ILoginValidator {
	boolean validate(User user, String password);
}
