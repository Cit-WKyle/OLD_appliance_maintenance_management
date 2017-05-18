package com.kyle.ie.tenant.models;

public class PropertyTenantComposite {

	private PropertyTenant tenant;
	
	private String username;
	private String email;
	private String propertyName;
	
	public PropertyTenant getTenant() {
		return tenant;
	}
	public void setTenant(PropertyTenant tenant) {
		this.tenant = tenant;
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
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	
}
