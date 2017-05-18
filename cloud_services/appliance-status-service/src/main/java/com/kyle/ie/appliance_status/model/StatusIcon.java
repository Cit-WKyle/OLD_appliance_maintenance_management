package com.kyle.ie.appliance_status.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class StatusIcon {

	private String s3Key;

	private String url;

	@DynamoDBAttribute
	public String getS3Key() {
		return s3Key;
	}

	public void setS3Key(String s3Key) {
		this.s3Key = s3Key;
	}

	@DynamoDBAttribute
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
