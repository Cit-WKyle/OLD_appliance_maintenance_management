package com.kyle.ie.property.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Address {

	private String _addressLine1;
	private String _addressLine2;
	private String _city;
	private String _state;
	private String _country;
	private String _postalCode;
	private float _longitude;
	private float _latitude;

	@DynamoDBAttribute
	public String getAddressLine1() {
		return _addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this._addressLine1 = addressLine1;
	}

	@DynamoDBAttribute
	public String getAddressLine2() {
		return _addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this._addressLine2 = addressLine2;
	}

	@DynamoDBAttribute
	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		this._city = city;
	}

	@DynamoDBAttribute
	public String getState() {
		return _state;
	}

	public void setState(String state) {
		this._state = state;
	}

	@DynamoDBAttribute
	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		this._country = country;
	}

	@DynamoDBAttribute
	public String getPostalCode() {
		return _postalCode;
	}

	public void setPostalCode(String postalCode) {
		this._postalCode = postalCode;
	}

	@DynamoDBAttribute
	public float getLongitude() {
		return _longitude;
	}

	public void setLongitude(float longitude) {
		this._longitude = longitude;
	}

	@DynamoDBAttribute
	public float getLatitude() {
		return _latitude;
	}

	public void setLatitude(float latitude) {
		this._latitude = latitude;
	}
}
