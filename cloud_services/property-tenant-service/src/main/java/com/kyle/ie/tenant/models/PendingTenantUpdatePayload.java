package com.kyle.ie.tenant.models;

public class PendingTenantUpdatePayload {

	private String status;
	private String pendingId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPendingId() {
		return pendingId;
	}
	public void setPendingId(String pendingId) {
		this.pendingId = pendingId;
	}
	
	
}
