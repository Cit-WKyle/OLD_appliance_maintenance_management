package com.kyle.ie.maintenance.scheduling.models;

import com.kyle.ie.maintenance.scheduling.models.constants.ScheduleStatus;

public class MaintenanceSchedule {

	private String id;
	
	private int startTimestamp;
	private int endTimestamp;
	private String repairReportId;
	private ScheduleStatus scheduleStatus;
	
	public int getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(int startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public int getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(int endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	public String getRepairReportId() {
		return repairReportId;
	}
	public void setRepairReportId(String repairReportId) {
		this.repairReportId = repairReportId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ScheduleStatus getScheduleStatus() {
		return scheduleStatus;
	}
	public void setScheduleStatus(ScheduleStatus scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	
	
}
