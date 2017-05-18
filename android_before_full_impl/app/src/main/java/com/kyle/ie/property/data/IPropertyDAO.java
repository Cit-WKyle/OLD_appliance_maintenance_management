package com.kyle.ie.property.data;

import com.kyle.ie.property.model.Property;

import java.util.List;

/**
 * Created by kyle_williamson on 12/02/2017.
 */

public interface IPropertyDAO {

    List<Property> getAll();

    Property getForId(String id);

    Property getForIndex(int index);
}
