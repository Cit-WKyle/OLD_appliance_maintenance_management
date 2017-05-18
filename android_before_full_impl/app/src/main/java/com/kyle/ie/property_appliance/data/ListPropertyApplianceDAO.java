package com.kyle.ie.property_appliance.data;

import com.kyle.ie.property_appliance.model.PropertyAppliance;

import java.util.List;

/**
 * Created by kyle_williamson on 16/02/2017.
 */

public class ListPropertyApplianceDAO implements IPropertyApplianceDAO {

    private List<PropertyAppliance> _list;

    public ListPropertyApplianceDAO(List<PropertyAppliance> propertyAppliances) {
        _list = propertyAppliances;
    }


    @Override
    public List<PropertyAppliance> getAll() {
        return _list;
    }

    @Override
    public PropertyAppliance getForId(String id) {
        for(PropertyAppliance pa: _list) {
            if(pa.getId().equals(id)) {
                return pa;
            }
        }
        return null;
    }

    @Override
    public PropertyAppliance getForIndex(int index) {
        return _list.get(index);
    }
}
