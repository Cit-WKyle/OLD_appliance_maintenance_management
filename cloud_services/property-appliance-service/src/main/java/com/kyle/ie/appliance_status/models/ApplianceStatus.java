package com.kyle.ie.appliance_status.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

public class ApplianceStatus {

	private String id;

	private String type;
	
	private String message;
	
	private StatusIcon icon;
	
	private String applianceType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@DynamoDBAttribute
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@DynamoDBAttribute
	public StatusIcon getIcon() {
		return icon;
	}

	public void setIcon(StatusIcon icon) {
		this.icon = icon;
	}

	public String getApplianceType() {
		return applianceType;
	}

	public void setApplianceType(String applianceType) {
		this.applianceType = applianceType;
	}
	
	
}
