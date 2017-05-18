package com.kyle.ie.property_appliance.clients.http;

import org.springframework.stereotype.Component;

import com.kyle.ie.appliance.models.Appliance;

@Component
public class ApplianceClientFallback implements IApplianceClient {

	@Override
	public Appliance getAppliance(String id) {
		return new Appliance();
	}

}
