package com.kyle.ie.auth.services;

import com.kyle.ie.auth.models.UserType;

public interface IUserTypeService {

	public UserType getUserTypeForUsername(String username);
}
