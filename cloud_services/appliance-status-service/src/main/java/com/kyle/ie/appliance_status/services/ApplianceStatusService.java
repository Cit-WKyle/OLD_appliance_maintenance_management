package com.kyle.ie.appliance_status.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.appliance_status.model.Status;
import com.kyle.ie.appliance_status.model.constants.ApplianceType;
import com.kyle.ie.appliance_status.model.constants.StatusType;
import com.kyle.ie.appliance_status.repositories.IApplianceStatusRepository;

@Service
public class ApplianceStatusService implements IApplianceStatusService {
	
	@Autowired
	private IApplianceStatusRepository _applStatRepo;

	@Override
	public Status getStatusForId(String id) {
		return _applStatRepo.findOne(id);
	}

	@Override
	public List<Status> getStatusesForIds(List<String> ids) {
		List<Status> list = new ArrayList<>();
		for(Status stat: _applStatRepo.findAll(ids)) {
			list.add(stat);
		}
		return list;
	}

	@Override
	public List<Status> getStatusesForApplianceType(ApplianceType type) {
		 List<Status> statuses = _applStatRepo.findByApplianceType(type);
		 statuses.addAll(_applStatRepo.findByApplianceType(ApplianceType.COMMON));
		 return statuses;
	}

	@Override
	public String getCommonStatusId(StatusType type) {
		return _applStatRepo.findByType(type).getId();
	}
	
	

}
