package com.kyle.ie.property_appliance.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kyle.ie.appliance.models.Appliance;
import com.kyle.ie.appliance_status.models.ApplianceStatus;

@JsonInclude(Include.NON_NULL)
public class PropertyApplianceComposite {
	
	private String _id;

	private Appliance _appliance;
	private ApplianceStatus _status;
	
	private String _name;
	private int _age;
	
	private List<StatusHistoryComposite> _statusHistory;
	
	public PropertyApplianceComposite() {
	}
	
	public String getId() {
		return _id;
	}
	
	public void setAppliance(Appliance appliance) {
		this._appliance = appliance;
	}
	
	public void setStatusHistory(List<StatusHistoryComposite> statusHistory) {
		this._statusHistory = statusHistory;
	}

	public Appliance getAppliance() {
		return _appliance;
	}

	public ApplianceStatus getStatus() {
		return _status;
	}
	
	public void setStatus(ApplianceStatus applStatus) {
		_status = applStatus;
	}

	public String getName() {
		return _name;
	}

	public int getAge() {
		return _age;
	}

	public List<StatusHistoryComposite> getStatusHistory() {
		return _statusHistory;
	}
}
