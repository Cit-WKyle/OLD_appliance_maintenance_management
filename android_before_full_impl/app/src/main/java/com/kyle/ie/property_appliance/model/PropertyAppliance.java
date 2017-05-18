package com.kyle.ie.property_appliance.model;

import com.kyle.ie.appliance.model.Appliance;

/**
 * Created by kyle_williamson on 15/02/2017.
 */

public class PropertyAppliance {

    private String _id;
    private String _propertyId;

    private Appliance _appliance;

    private String _name;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getPropertyId() {
        return _propertyId;
    }

    public void setPropertyId(String propertyId) {
        this._propertyId = propertyId;
    }

    public Appliance getAppl() {
        return _appliance;
    }

    public void setAppl(Appliance appl) {
        this._appliance = appl;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    private int age;


}
