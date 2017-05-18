package com.kyle.ie.property.models;
import java.util.List;

public class Property {

	private String _propId;

	private Address _address;
	private int _age;
	private int _bedCount;
	private int _bathroomCount;

	private int _managerId;

	private List<Integer> _prevManagers;
	private List<Integer> _prevTennants;
	private List<Integer> _currTennants;
	private List<Integer> _prevAppliances;

	public String getPropId() {
		return _propId;
	}

	public void setPropId(String id) {
		this._propId = id;
	}

	public int getManagerId() {
		return _managerId;
	}

	public void setManagerId(int managerId) {
		_managerId = managerId;
	}

	public Address getAddress() {
		return _address;
	}

	public void setAddress(Address address) {
		this._address = address;
	}

	public int getAge() {
		return _age;
	}

	public void setAge(int age) {
		this._age = age;
	}

	public int getBedCount() {
		return _bedCount;
	}

	public void setBedCount(int bedCount) {
		this._bedCount = bedCount;
	}

	public int getBathroomCount() {
		return _bathroomCount;
	}

	public void setBathroomCount(int bathroomCount) {
		this._bathroomCount = bathroomCount;
	}

	public List<Integer> getPrevManagers() {
		return _prevManagers;
	}

	public void setPrevManagers(List<Integer> prevManagers) {
		this._prevManagers = prevManagers;
	}

	public List<Integer> getPrevTennants() {
		return _prevTennants;
	}

	public void setPrevTennants(List<Integer> prevTennants) {
		this._prevTennants = prevTennants;
	}

	public List<Integer> getCurrTennants() {
		return _currTennants;
	}

	public void setCurrTennants(List<Integer> currTennants) {
		this._currTennants = currTennants;
	}

	public List<Integer> getPrevAppliances() {
		return _prevAppliances;
	}

	public void setPrevAppliances(List<Integer> prevAppliances) {
		this._prevAppliances = prevAppliances;
	}
}
