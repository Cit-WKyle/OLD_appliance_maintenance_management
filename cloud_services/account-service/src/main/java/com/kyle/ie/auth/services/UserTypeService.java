package com.kyle.ie.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kyle.ie.auth.models.UserType;
import com.kyle.ie.auth.repositories.IUserRepository;

@Service
public class UserTypeService implements IUserTypeService {
	private static final String USER_NOT_FOUND = "User not found: ";
	
	@Autowired
	private IUserRepository _repo;

	@Override
	public UserType getUserTypeForUsername(String username) {
		UserType type = _repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + username)).getUserType();
		return type;
	}

}
