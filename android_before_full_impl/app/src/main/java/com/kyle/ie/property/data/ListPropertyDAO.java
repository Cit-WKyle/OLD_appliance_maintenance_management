package com.kyle.ie.property.data;

import com.kyle.ie.property.model.Property;

import java.util.List;

/**
 * Created by kyle_williamson on 12/02/2017.
 */

public class ListPropertyDAO implements IPropertyDAO {

    private List<Property> _list;

    public ListPropertyDAO(List<Property> properties) {
        _list = properties;
    }

    @Override
    public List<Property> getAll() {
        return _list;
    }

    @Override
    public Property getForId(String id) {
        for(Property p: _list) {
            if(p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Property getForIndex(int index) {
        return _list.get(index);
    }
}
