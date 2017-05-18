package com.kyle.ie.auth.models;

public class UserDetails {

	private Long id;
	private String username;
	private String email;
	private String type;
	
	public UserDetails(Long id, String username, String email, String type) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
