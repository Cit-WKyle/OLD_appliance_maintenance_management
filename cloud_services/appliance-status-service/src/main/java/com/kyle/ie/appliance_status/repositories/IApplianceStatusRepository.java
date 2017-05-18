package com.kyle.ie.appliance_status.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.appliance_status.model.Status;
import com.kyle.ie.appliance_status.model.constants.ApplianceType;
import com.kyle.ie.appliance_status.model.constants.StatusType;

@EnableScan
public interface IApplianceStatusRepository extends CrudRepository<Status, String> {

	List<Status> findByApplianceType(ApplianceType type);
	
	Status findByType(StatusType type);
}
