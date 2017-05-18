package com.kyle.ie.report.diagnostic.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.kyle.ie.report.diagnostic.models.DiagnosticReport;

@EnableScan
public interface IDiagnosticReportRepository extends CrudRepository<DiagnosticReport, String> {

	List<DiagnosticReport> findByManagerId(int managerId);
}
