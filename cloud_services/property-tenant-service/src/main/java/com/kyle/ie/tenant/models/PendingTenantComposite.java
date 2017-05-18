package com.kyle.ie.tenant.models;

public class PendingTenantComposite {

	private PendingTenant tenant;
	
	private String username;
	
	private String propertyName;

	public PendingTenant getTenant() {
		return tenant;
	}

	public void setTenant(PendingTenant tenant) {
		this.tenant = tenant;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	
}
