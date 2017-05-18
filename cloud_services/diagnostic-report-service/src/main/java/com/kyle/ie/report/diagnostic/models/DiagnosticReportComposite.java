package com.kyle.ie.report.diagnostic.models;

import java.util.List;

import com.kyle.ie.property_appliance.models.PropertyApplianceComposite;

public class DiagnosticReportComposite {
	
	private String id;
	
	private PropertyApplianceComposite propAppl;
	
	private int managerId;
	
	private List<String> organisations;

	public DiagnosticReportComposite(DiagnosticReport report) {
		id = report.getId();
		managerId = report.getManagerId();
		organisations = report.getOrganisations();
	}

	public String getId() {
		return id;
	}

	public void setDiagRepId(String id) {
		this.id = id;
	}

	public PropertyApplianceComposite getPropAppl() {
		return propAppl;
	}

	public void setPropAppl(PropertyApplianceComposite propAppl) {
		this.propAppl = propAppl;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public List<String> getOrganisations() {
		return organisations;
	}

	public void setOrganisations(List<String> organisations) {
		this.organisations = organisations;
	}
	
}
