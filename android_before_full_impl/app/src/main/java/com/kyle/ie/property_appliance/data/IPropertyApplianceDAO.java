package com.kyle.ie.property_appliance.data;

import com.kyle.ie.property_appliance.model.PropertyAppliance;

import java.util.List;

/**
 * Created by kyle_williamson on 16/02/2017.
 */

public interface IPropertyApplianceDAO {

    List<PropertyAppliance> getAll();

    PropertyAppliance getForId(String id);

    PropertyAppliance getForIndex(int index);
}
