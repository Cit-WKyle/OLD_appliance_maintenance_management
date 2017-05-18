package com.kyle.ie.property_appliance.controller;

import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.property.model.Property;
import com.kyle.ie.property_appliance.data.IPropertyApplianceDAO;
import com.kyle.ie.property_appliance.model.PropertyAppliance;

import java.util.List;

/**
 * Created by kyle_williamson on 16/02/2017.
 */

public interface IPropertyApplianceController {

    void getProperyAppliancesForProperty(Property property, ICallback<List<String>> callback);

    IPropertyApplianceDAO getDAO();
}
