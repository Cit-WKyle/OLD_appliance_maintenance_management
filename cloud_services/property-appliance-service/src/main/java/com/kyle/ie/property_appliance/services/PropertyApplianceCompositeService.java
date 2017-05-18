package com.kyle.ie.property_appliance.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.appliance.models.Appliance;
import com.kyle.ie.appliance_status.models.ApplianceStatus;
import com.kyle.ie.property_appliance.clients.http.IApplianceClient;
import com.kyle.ie.property_appliance.clients.http.IApplianceStatusClient;
import com.kyle.ie.property_appliance.models.PropertyAppliance;
import com.kyle.ie.property_appliance.models.PropertyApplianceComposite;

@Service
public class PropertyApplianceCompositeService implements IPropertyApplianceCompositeService {

	@Autowired
	private IPropertyApplianceService _propApplService;
	
	@Autowired
	private IStatusHistoryCompositeService _statHistCompService;
	
	@Autowired
	private IApplianceClient _applClient;
	
	@Autowired
	private IApplianceStatusClient _applStatClient;
	
	@Override
	public List<PropertyApplianceComposite> getPropertyAppliancesForProperty(String id) {
		List<PropertyAppliance> pApplList = _propApplService.getPropertyAppliancesForProperty(id);
		List<PropertyApplianceComposite> pApplCompList = new ArrayList<>();
		
		for(PropertyAppliance pApp: pApplList) {
			PropertyApplianceComposite pApplComp = compose(pApp);
			pApplCompList.add(pApplComp);
		}
		return pApplCompList;
	}

	@Override
	public PropertyApplianceComposite generatePropertyApplianceComposite(PropertyAppliance propAppl) {
		return compose(propAppl);
	}
	
	private PropertyApplianceComposite compose(PropertyAppliance pApp) {
		PropertyApplianceComposite pApplComp = new PropertyApplianceComposite(pApp);
		Appliance appl = _applClient.getAppliance(pApp.getApplianceId());
		ApplianceStatus applStat = _applStatClient.getApplianceStatus(pApp.getStatusId());
		pApplComp.setAppliance(appl);
		pApplComp.setStatus(applStat);
		pApplComp.setStatusHistory(_statHistCompService.composeStatusHistoryList(pApp.getStatusHistory()));
		return pApplComp;
	}

}
