package com.kyle.ie.property.services;

import java.util.List;

import com.kyle.ie.property.form.PropertyForm;
import com.kyle.ie.property.models.Property;

public interface IPropertyService {

	public List<Property> getPropertiesForManager(int managerId);
	
	public Property save(PropertyForm form);
	
	public Property get(String id);
}
