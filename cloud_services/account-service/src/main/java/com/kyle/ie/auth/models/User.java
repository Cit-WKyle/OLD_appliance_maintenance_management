package com.kyle.ie.auth.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 ********************************************************************
 * Modal object representing a user of the application.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	
	@Column(name="username", unique=true)
	private String _username;
	
	@Column(name="password") 
	private String _password;
	
	@Column(name="email", unique = true)
	private String _email;
	
	@OneToMany
	@JoinColumn(name="app_user_id", referencedColumnName="id")
	private List<UserRole> _roles;
	
	@ManyToOne
	@JoinColumn(name="user_type_id")
	private UserType _userType;
	
	public Long getId() {
		return _id;
	}
	
	public void setId(Long id) {
		_id = id;
	}
	
	public String getUsername() {
		return _username;
	}
	
	public void setUsername(String username) {
		_username = username;
	}
	
	@JsonIgnore
	public String getPassword() {
		return _password;
	}
	
	public void setPassword(String password) {
		_password = password;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setEmail(String email) {
		_email = email;
	}
	
    public List<UserRole> getRoles() {
        return _roles;
    }
    
    public void setRoles(List<UserRole> roles) {
    	_roles = roles;
    }
    
    public UserType getUserType() {
    	return _userType;
    }
    
    public void setUserType(UserType userType) {
    	_userType = userType;
    }
}
