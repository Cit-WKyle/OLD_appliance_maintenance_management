package com.kyle.ie.maintenance.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.maintenance.models.MaintenanceOrganisation;

@EnableScan
public interface IMaintenanceOrganisationRepository extends CrudRepository<MaintenanceOrganisation, String> {

}
