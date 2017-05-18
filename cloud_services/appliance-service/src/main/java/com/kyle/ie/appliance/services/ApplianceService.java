package com.kyle.ie.appliance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kyle.ie.appliance.models.Appliance;
import com.kyle.ie.appliance.models.constants.ApplianceType;
import com.kyle.ie.appliance.repositories.IApplianceRepository;

@Service
public class ApplianceService implements IApplianceService {
	
	@Autowired
	private IApplianceRepository _applRepo;

	@Override
	public List<Appliance> getAppliances() {
		return Lists.newArrayList(_applRepo.findAll());
	}

	@Override
	public List<Appliance> getAppliancesByType(ApplianceType type) {
		return _applRepo.findByType(type);
	}

	@Override
	public Appliance getApplianceForId(String id) {
		return _applRepo.findOne(id);
	}

}
