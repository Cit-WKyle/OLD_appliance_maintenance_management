package com.kyle.ie.report.diagnostic.validators;

import java.util.List;

import com.kyle.ie.report.diagnostic.form.DiagnosticReportForm;

public interface IDiagnosticReportValidator {
	
	boolean validate(DiagnosticReportForm form);
	
	List<String> getErrors();
}
