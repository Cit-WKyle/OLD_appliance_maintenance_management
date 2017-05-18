package com.kyle.ie.property_appliance.models;

import com.kyle.ie.appliance_status.models.ApplianceStatus;

public class StatusHistoryComposite {

	private ApplianceStatus status;
	
	private int dateTime;
	
	public StatusHistoryComposite(StatusHistory statusHistory) {
		this.dateTime = statusHistory.getDateTime();
		status = new ApplianceStatus();
	}
	
	public void setStatus(ApplianceStatus status) {
		this.status = status;
	}

	public ApplianceStatus getStatus() {
		return status;
	}

	public int getDateTime() {
		return dateTime;
	}
}
