package com.kyle.ie.property_appliance.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.property_appliance.models.PropertyAppliance;

@EnableScan
public interface IPropertyApplianceRepository extends CrudRepository<PropertyAppliance, String> {
	
	List<PropertyAppliance> findByPropertyId(String id);

}
