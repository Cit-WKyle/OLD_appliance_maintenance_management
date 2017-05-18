package com.kyle.ie.property.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.property.form.PropertyForm;
import com.kyle.ie.property.models.Property;
import com.kyle.ie.property.repositories.IPropertyRepository;

@Service
public class PropertyService implements IPropertyService {
	
	@Autowired
	private IPropertyRepository _repo;

	@Override
	public List<Property> getPropertiesForManager(int managerId) {
		return _repo.findByManagerId(managerId);
	}

	@Override
	public Property save(PropertyForm form) {
		Property prop = new Property();
		prop.setAddress(form.getAddress());
		prop.setAge(form.getAge());
		prop.setBathroomCount(form.getBathroomCount());
		prop.setBedCount(form.getBedCount());
		prop.setCurrTennants(new ArrayList<Integer>());
		prop.setManagerId(form.getManagerId());
		prop.setPrevAppliances(new ArrayList<Integer>());
		prop.setPrevManagers(new ArrayList<Integer>());
		prop.setPrevTennants(new ArrayList<Integer>());
		
		_repo.save(prop);
		
		return prop;
	}

	@Override
	public Property get(String id) {
		return _repo.findOne(id);
	}

}
