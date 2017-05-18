package com.kyle.ie.appliance_status.models;

public class StatusNotificationPayload {

	private String propertyApplianceId;
	private String propertyApplianceName;
	
	private Status previousStatus;
	private Status newStatus;

	public String getPropertyApplianceId() {
		return propertyApplianceId;
	}
	public void setPropertyApplianceId(String propertyApplianceId) {
		this.propertyApplianceId = propertyApplianceId;
	}
	public String getPropertyApplianceName() {
		return propertyApplianceName;
	}
	public void setPropertyApplianceName(String propertyApplianceName) {
		this.propertyApplianceName = propertyApplianceName;
	}
	public Status getPreviousStatus() {
		return previousStatus;
	}
	public void setPreviousStatus(Status previousStatus) {
		this.previousStatus = previousStatus;
	}
	public Status getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(Status newStatus) {
		this.newStatus = newStatus;
	}
	
}
