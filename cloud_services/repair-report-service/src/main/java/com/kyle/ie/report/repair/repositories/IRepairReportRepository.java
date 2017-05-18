package com.kyle.ie.report.repair.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.report.repair.models.RepairReport;

@EnableScan
public interface IRepairReportRepository extends CrudRepository<RepairReport, String> {

}
