package com.kyle.ie.property_appliance.models;

public class StatusUpdatePayload {
	
	private String propertyApplianceId;
	private String statusId;
	
	public String getPropertyApplianceId() {
		return propertyApplianceId;
	}
	public void setPropertyApplianceId(String propertyApplianceId) {
		this.propertyApplianceId = propertyApplianceId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	

}
