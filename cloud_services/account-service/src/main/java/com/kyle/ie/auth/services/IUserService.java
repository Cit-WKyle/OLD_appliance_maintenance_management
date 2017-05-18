package com.kyle.ie.auth.services;

import java.util.Optional;

import com.kyle.ie.auth.forms.RegisterForm;
import com.kyle.ie.auth.models.User;

public interface IUserService {
    public Optional<User> getByUsername(String username);
    
    public User register(RegisterForm form);
    
    public String getEmailForUsername(String username);
}
