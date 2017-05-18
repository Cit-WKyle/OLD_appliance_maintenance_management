package com.kyle.ie.maintenance.models.web;

public class DiagReportResponsePayload {

	private int engineerId;
	private String diagRepId;
	
	public DiagReportResponsePayload(int engineerId, String diagRepId) {
		this.engineerId = engineerId;
		this.diagRepId = diagRepId;
	}
	
	public String getDiagRepId() {
		return diagRepId;
	}
	public void setDiagRepId(String diagRepId) {
		this.diagRepId = diagRepId;
	}
	public int getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(int engineerId) {
		this.engineerId = engineerId;
	}
	
}
