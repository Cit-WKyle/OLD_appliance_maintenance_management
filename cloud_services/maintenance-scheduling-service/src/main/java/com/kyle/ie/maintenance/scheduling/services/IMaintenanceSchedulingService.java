package com.kyle.ie.maintenance.scheduling.services;

import com.kyle.ie.maintenance.scheduling.forms.ProposedTime;
import com.kyle.ie.maintenance.scheduling.models.MaintenanceSchedule;

public interface IMaintenanceSchedulingService {

	MaintenanceSchedule create(String reportId, ProposedTime time);
	
	void acceptSchedule(String scheduleId);
	
	void declineSchedule(String scheduleId);
}
