package com.kyle.ie.property.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.property.models.Property;

@EnableScan
public interface IPropertyRepository extends CrudRepository<Property, String>{

	public List<Property> findByManagerId(int managerId);
}
