package com.kyle.ie.property;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kyle.ie.property.models.Address;
import com.kyle.ie.property.models.Property;
import com.kyle.ie.property.repositories.IPropertyRepository;

public class DBSeeder implements IDBMSSeeder<Property> {
	
	@Autowired
	private IPropertyRepository _repo;

	@Override
	public void run(String... args) throws Exception {
		
		if(_repo.count() == 0) {
			List<Property> properties = generateData();
			
			_repo.save(properties);
		}
	}

	@Override
	public List<Property> generateData() {
		List<Property> properties = new ArrayList<>();
		
		Address add1 = new Address();
		add1.setAddressLine1("House Name");
		add1.setAddressLine2("Street Name");
		add1.setCity("City");
		add1.setCountry("Country");
		add1.setPostalCode("0000");
		add1.setState("County");
		add1.setLatitude(0);
		add1.setLongitude(0);

		Address add2 = new Address();
		add2.setAddressLine1("House Name 2");
		add2.setAddressLine2("Street Name 2");
		add2.setCity("City 2");
		add2.setCountry("Country 2");
		add2.setPostalCode("0001");
		add2.setState("County 2");
		add2.setLatitude(0);
		add2.setLongitude(0);
		
		Property prop1 = new Property();
		prop1.setPropId("5ca17a81-0ada-46f1-817f-8c524dd6a6c6");
		prop1.setAddress(add1);
		prop1.setAge(10);
		prop1.setBathroomCount(1);
		prop1.setBedCount(2);
		prop1.setCurrTennants(new ArrayList<Integer>());
		prop1.setManagerId(1);
		prop1.setPrevAppliances(new ArrayList<Integer>());
		prop1.setPrevManagers(new ArrayList<Integer>());
		prop1.setPrevTennants(new ArrayList<Integer>());
		
		Property prop2 = new Property();
		prop2.setPropId("f790a9e5-e8e9-49b2-a038-7337ff61e0ec");
		prop2.setAddress(add2);
		prop2.setAge(12);
		prop2.setBathroomCount(2);
		prop2.setBedCount(3);
		prop2.setCurrTennants(new ArrayList<Integer>());
		prop2.setManagerId(1);
		prop2.setPrevAppliances(new ArrayList<Integer>());
		prop2.setPrevManagers(new ArrayList<Integer>());
		prop2.setPrevTennants(new ArrayList<Integer>());
		
		properties.add(prop1);
		properties.add(prop2);
		
		return properties;
	}

}
