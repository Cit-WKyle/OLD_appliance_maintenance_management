package com.kyle.ie.report.repair.services;

import com.kyle.ie.report.repair.forms.RepairReportForm;
import com.kyle.ie.report.repair.models.RepairReport;

public interface IRepairReportService {

	void declineReport(String id);
	
	void acceptReport(String id);
	
	RepairReport create(RepairReportForm form);
}
