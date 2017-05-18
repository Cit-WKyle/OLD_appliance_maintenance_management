package com.kyle.ie.report.diagnostic.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kyle.ie.report.diagnostic.form.DiagnosticReportForm;

@Component
public class DiagnosticReportValidator implements IDiagnosticReportValidator {
	
	private static final String DESC_LENGTH_ERR = "Description must be between 4 and 100 characters.";
	
	private List<String> _errors;
	
	public DiagnosticReportValidator() {
		_errors = new ArrayList<String>();
	}
	
	@Override
	public boolean validate(DiagnosticReportForm form) {
		boolean validDesc = validateDescription(form.getDescription());
		boolean validPAId = validatePropApplId(form.getPropertyApplianceId());
		return validDesc && validPAId;
	}

	@Override
	public List<String> getErrors() {
		return _errors;
	}
	
	private boolean validateDescription(String desc) {
		if(desc.length() > 4 && desc.length() < 100) {
			return true;
		} else {
			_errors.add(DESC_LENGTH_ERR);
			return false;
		}
	}
	
	private boolean validatePropApplId(String id) {
		return true;
	}

}
