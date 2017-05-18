package com.kyle.ie.maintenance.scheduling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kyle.ie.maintenance.scheduling.forms.ProposedTime;
import com.kyle.ie.maintenance.scheduling.models.MaintenanceSchedule;
import com.kyle.ie.maintenance.scheduling.models.constants.ScheduleStatus;
import com.kyle.ie.maintenance.scheduling.repositories.IMaintenanceSchedulingRepository;

@Service
public class MaintenanceSchedulingService implements IMaintenanceSchedulingService {
	
	@Autowired
	private IMaintenanceSchedulingRepository _repo;

	@Override
	public MaintenanceSchedule create(String reportId, ProposedTime time) {
		MaintenanceSchedule sched = new MaintenanceSchedule();
		sched.setEndTimestamp(time.getEndTimestamp());
		sched.setRepairReportId(reportId);
		sched.setScheduleStatus(ScheduleStatus.DECLINED);
		sched.setStartTimestamp(time.getStartTimestamp());
		_repo.save(sched);
		return sched;
	}

	@Override
	public void acceptSchedule(String scheduleId) {
		MaintenanceSchedule sched = _repo.findOne(scheduleId);
		List<MaintenanceSchedule> list = Lists.newArrayList(_repo.findAll());
		for(MaintenanceSchedule item : list) {
			if(item.getRepairReportId().equals(sched.getRepairReportId())) {
				item.setScheduleStatus(ScheduleStatus.DECLINED);
			}
		}
		
		_repo.save(list);
		sched.setScheduleStatus(ScheduleStatus.ACCEPTED);
		_repo.save(sched);
	}

	@Override
	public void declineSchedule(String scheduleId) {
		// TODO Auto-generated method stub
		
	}

}
