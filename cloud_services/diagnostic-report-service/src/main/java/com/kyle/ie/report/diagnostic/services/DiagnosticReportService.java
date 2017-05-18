package com.kyle.ie.report.diagnostic.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kyle.ie.report.diagnostic.form.DiagnosticReportForm;
import com.kyle.ie.report.diagnostic.models.DiagnosticReport;
import com.kyle.ie.report.diagnostic.repositories.IDiagnosticReportRepository;

@Service
public class DiagnosticReportService implements IDiagnosticReportService {
	
	@Autowired
	private IDiagnosticReportRepository _reportRepo;

	@Override
	public List<DiagnosticReport> getForManagerId(int id) {
		return _reportRepo.findByManagerId(id);
	}

	@Override
	public List<DiagnosticReport> getForOrganisationId(String id) {
		List<DiagnosticReport> reports = Lists.newArrayList(_reportRepo.findAll());
		List<DiagnosticReport> orgReports = new ArrayList<>();
		for(DiagnosticReport report: reports) {
			for(String orgId: report.getOrganisations()) {
				if(orgId.equals(id)) {
					orgReports.add(report);
				}
			}
		}
		return orgReports;
	}

	@Override
	public DiagnosticReport createFromForm(DiagnosticReportForm form) {
		DiagnosticReport report = new DiagnosticReport();
		report.setDescription(form.getDescription());
		report.setOrganisations(new ArrayList<String>());
		report.setTimestamp((int) new DateTime().getMillis());
		report.setPropApplId(form.getPropertyApplianceId());
		_reportRepo.save(report);
		return report;
	}

}
