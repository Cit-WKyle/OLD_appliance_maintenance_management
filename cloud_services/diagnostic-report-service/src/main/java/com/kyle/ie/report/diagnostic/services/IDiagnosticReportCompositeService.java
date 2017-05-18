package com.kyle.ie.report.diagnostic.services;

import java.util.List;

import com.kyle.ie.report.diagnostic.models.DiagnosticReport;
import com.kyle.ie.report.diagnostic.models.DiagnosticReportComposite;

public interface IDiagnosticReportCompositeService {

	DiagnosticReportComposite compose(DiagnosticReport report, String token);
	
	List<DiagnosticReportComposite> compose(List<DiagnosticReport> report, String token);
}
