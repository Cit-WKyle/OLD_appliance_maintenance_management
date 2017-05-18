package com.kyle.ie.report.repair.validators;

import java.util.List;

import com.kyle.ie.report.repair.forms.RepairReportForm;

public interface IRepairReportFormValidator {

	boolean validate(RepairReportForm form);
	
	List<String> getErrors();
}
