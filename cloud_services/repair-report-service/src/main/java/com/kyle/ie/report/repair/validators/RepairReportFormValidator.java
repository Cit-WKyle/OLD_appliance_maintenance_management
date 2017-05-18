package com.kyle.ie.report.repair.validators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kyle.ie.report.repair.forms.RepairReportForm;

@Component
public class RepairReportFormValidator implements IRepairReportFormValidator {

	private static final String DESC_LENGTH_ERR = "Description must be between 4 and 100 characters.";
	private static final String COST_VAL_ERR = "Cost must be between 10 and 2000.";
	private static final String EST_DUR_ERR = "Estimated duration must be between 1 and 12 hours.";
	
	private static final int MIN_COST = 10;
	private static final int MAX_COST = 2000;
	
	private List<String> _errors;
	
	public RepairReportFormValidator() {
		_errors = new ArrayList<>();
	}
	
	public boolean validate(RepairReportForm form) {
		boolean costValid = validateCost(form.getCost());
		boolean descValid = validateDesc(form.getDescription());
		boolean estDurValid = validateEstDuration(form.getEstDuration());
		return costValid && descValid && estDurValid;
	}
	
	public List<String> getErrors() {
		return _errors;
	}
	
	private boolean validateCost(BigDecimal cost) {
		if(cost.compareTo(new BigDecimal(MIN_COST)) != -1 && cost.compareTo(new BigDecimal(MAX_COST)) != 1) {
			return true;
		} else {
			_errors.add(COST_VAL_ERR);
			return false;
		}
	}
	
	private boolean validateDesc(String desc) {
		if(desc.length() >= 4 && desc.length() <= 100) {
			return true;
		} else {
			_errors.add(DESC_LENGTH_ERR);
			return false;
		}
	}
	
	private boolean validateEstDuration(int estimatedDur) {
		if(estimatedDur > 0 && estimatedDur < 13) {
			return true;
		} else {
			_errors.add(EST_DUR_ERR);
			return false;
		}
	}
}
