package com.kyle.ie.property.form;

import com.kyle.ie.property.models.Address;

public class PropertyForm {

	private Address address;
	
	private int age;
	private int bedCount;
	private int bathroomCount;
	
	private int managerId;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBedCount() {
		return bedCount;
	}

	public void setBedCount(int bedCount) {
		this.bedCount = bedCount;
	}

	public int getBathroomCount() {
		return bathroomCount;
	}

	public void setBathroomCount(int bathroomCount) {
		this.bathroomCount = bathroomCount;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
}
