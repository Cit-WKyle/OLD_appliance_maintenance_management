package com.kyle.ie.tenant.models;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kyle.ie.marshaller.EnumMarshaller;
import com.kyle.ie.tenant.models.constants.PendingStatus;

@DynamoDBTable(tableName = "PendingTenant")
public class PendingTenant {

	private String id;
	
	private String propertyId;
	
	private int accountId;
	
	private PendingStatus status;

	@Id
	@DynamoDBHashKey(attributeName = "pendTntId")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	@DynamoDBAttribute
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public PendingStatus getStatus() {
		return status;
	}

	public void setStatus(PendingStatus status) {
		this.status = status;
	}
	
	
}
