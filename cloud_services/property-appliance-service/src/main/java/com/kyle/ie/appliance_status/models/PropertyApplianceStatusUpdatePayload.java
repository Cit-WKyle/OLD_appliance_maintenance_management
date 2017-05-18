package com.kyle.ie.appliance_status.models;

public class PropertyApplianceStatusUpdatePayload {

	private String propertyApplianceId;
	private String propertyApplianceName;
	private String oldStatusId;
	private String newStatusId;
	
	public String getPropertyApplianceId() {
		return propertyApplianceId;
	}
	public void setPropertyApplianceId(String propertyApplianceId) {
		this.propertyApplianceId = propertyApplianceId;
	}
	public String getOldStatusId() {
		return oldStatusId;
	}
	public void setOldStatusId(String oldStatusId) {
		this.oldStatusId = oldStatusId;
	}
	public String getNewStatusId() {
		return newStatusId;
	}
	public void setNewStatusId(String newStatusId) {
		this.newStatusId = newStatusId;
	}
	public String getPropertyApplianceName() {
		return propertyApplianceName;
	}
	public void setPropertyApplianceName(String propertyApplianceName) {
		this.propertyApplianceName = propertyApplianceName;
	}
	
	
}
