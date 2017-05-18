package com.kyle.ie.property_appliance.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class StatusHistory {

	private String _statusId;
	
	private int _dateTime;
	
	public StatusHistory() {}

	@DynamoDBAttribute
	public String getStatusId() {
		return _statusId;
	}

	public void setStatusId(String statusId) {
		this._statusId = statusId;
	}

	@DynamoDBAttribute
	public int getDateTime() {
		return _dateTime;
	}

	public void setDateTime(int dateTime) {
		this._dateTime = dateTime;
	}
	
}
