package com.kyle.ie.auth.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kyle.ie.auth.forms.RegisterForm;
import com.kyle.ie.auth.models.Role;
import com.kyle.ie.auth.models.User;
import com.kyle.ie.auth.models.UserRole;
import com.kyle.ie.auth.repositories.IUserRepository;
import com.kyle.ie.auth.repositories.IUserTypeRepository;

@Component
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository _userRepo;
	
	@Autowired
	private IUserTypeRepository _typeRepo;

	@Override
	public Optional<User> getByUsername(String username) {
		return _userRepo.findByUsername(username);
	}

	@Override
	public User register(RegisterForm form) {
    	
    	User user = new User();
    	user.setEmail(form.getEmail());
    	user.setPassword(form.getEmail());
    	user.setUsername(form.getUsername());
    	user.setUserType(_typeRepo.findBy_type(form.getUserType()));
    	
    	List<UserRole> roles = new ArrayList<>();
    	UserRole role = new UserRole();
    	role.setRole(Role.MEMBER);
    	roles.add(role);
    	
    	_userRepo.save(user);
    	
		return user;
	}

	@Override
	public String getEmailForUsername(String username) {
		return _userRepo.findByUsername(username).get().getEmail();
	}

}
