package com.kyle.ie.maintenance.scheduling.forms;

import java.util.List;

public class MaintenanceScheduleForm {

	private String repairReportId;

	private List<ProposedTime> proposedTimes;

	public String getRepairReportId() {
		return repairReportId;
	}

	public void setRepairReportId(String repairReportId) {
		this.repairReportId = repairReportId;
	}

	public List<ProposedTime> getProposedTimes() {
		return proposedTimes;
	}

	public void setProposedTimes(List<ProposedTime> proposedTimes) {
		this.proposedTimes = proposedTimes;
	}
	
	
}
