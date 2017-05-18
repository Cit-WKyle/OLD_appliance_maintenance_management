package com.kyle.ie.appliance_status.model;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kyle.ie.appliance_status.marshaller.EnumMarshaller;
import com.kyle.ie.appliance_status.model.constants.ApplianceType;
import com.kyle.ie.appliance_status.model.constants.StatusType;

@DynamoDBTable(tableName = "ApplianceStatus")
public class Status {
	
	private String id;

	private StatusType type;
	
	private String message;
	
	private StatusIcon icon;
	
	private ApplianceType applianceType;

	@Id
	@DynamoDBHashKey(attributeName = "statusId")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public StatusType getType() {
		return type;
	}

	public void setType(StatusType type) {
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

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public ApplianceType getApplianceType() {
		return applianceType;
	}

	public void setApplianceType(ApplianceType applianceType) {
		this.applianceType = applianceType;
	}
	
	
}
