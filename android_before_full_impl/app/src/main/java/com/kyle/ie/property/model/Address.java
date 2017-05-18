package com.kyle.ie.property.model;

public class Address {

	private String _addressLine1;
	private String _addressLine2;
	private String _city;
	private String _state;
	private String _country;
	private String _postalCode;
	private float _longitude;
	private float _latitude;


	public String getAddressLine1() {
		return _addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this._addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return _addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this._addressLine2 = addressLine2;
	}


	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		this._city = city;
	}


	public String getState() {
		return _state;
	}

	public void setState(String state) {
		this._state = state;
	}


	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		this._country = country;
	}


	public String getPostalCode() {
		return _postalCode;
	}

	public void setPostalCode(String postalCode) {
		this._postalCode = postalCode;
	}


	public float getLongitude() {
		return _longitude;
	}

	public void setLongitude(float longitude) {
		this._longitude = longitude;
	}


	public float getLatitude() {
		return _latitude;
	}

	public void setLatitude(float latitude) {
		this._latitude = latitude;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Line 1: " + _addressLine1 + "\t");
		builder.append("Line 2: " + _addressLine2 + "\t");
		builder.append("City: " + _city + "\t");
		builder.append("State: " + _state + "\t");
		builder.append("Country: " + _country + "\t");
		builder.append("Postal Code: " + _postalCode + "\t");
		builder.append("Longtide: " + _longitude + "\t");
		builder.append("Latitude: " + _latitude + "\t");
		return builder.toString();
	}
}
