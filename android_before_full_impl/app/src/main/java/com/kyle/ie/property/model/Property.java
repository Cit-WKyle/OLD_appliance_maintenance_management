package com.kyle.ie.property.model;

/**
 * Created by kyle_williamson on 12/02/2017.
 */

public class Property {

    private String _id;

    private Address _address;
    private int _age;
    private int _bedCount;
    private int _bathroomCount;

    private int _managerId;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: " + _id + " \n");
        builder.append("Address: " + _address + " \n");
        builder.append("Age: " + _age + " \n");
        builder.append("Bed Count: " + _bedCount + " \n");
        builder.append("Bathroom Count: " + _bathroomCount + " \n");
        builder.append("Manager ID: " + _managerId + " \n");
        return builder.toString();
    }

}
