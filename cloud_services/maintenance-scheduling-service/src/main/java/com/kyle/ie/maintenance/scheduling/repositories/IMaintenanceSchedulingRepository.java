package com.kyle.ie.maintenance.scheduling.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.maintenance.scheduling.models.MaintenanceSchedule;

@EnableScan
public interface IMaintenanceSchedulingRepository extends CrudRepository<MaintenanceSchedule, String>{

}
