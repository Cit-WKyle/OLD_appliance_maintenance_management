package com.kyle.ie.report.diagnostic.services;

import java.util.List;

import com.kyle.ie.report.diagnostic.form.DiagnosticReportForm;
import com.kyle.ie.report.diagnostic.models.DiagnosticReport;

public interface IDiagnosticReportService {

	List<DiagnosticReport> getForManagerId(int id);
	
	List<DiagnosticReport> getForOrganisationId(String id);
	
	DiagnosticReport createFromForm(DiagnosticReportForm form);
}
