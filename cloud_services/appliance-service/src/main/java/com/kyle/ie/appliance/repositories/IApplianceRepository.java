package com.kyle.ie.appliance.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.appliance.models.Appliance;
import com.kyle.ie.appliance.models.constants.ApplianceType;

@EnableScan
public interface IApplianceRepository extends CrudRepository<Appliance, String> {
	
	List<Appliance> findByType(ApplianceType type);
}
