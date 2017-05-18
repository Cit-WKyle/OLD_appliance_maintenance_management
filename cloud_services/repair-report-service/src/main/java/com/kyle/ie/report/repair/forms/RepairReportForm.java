package com.kyle.ie.report.repair.forms;

import java.math.BigDecimal;

public class RepairReportForm {
	
	private int engineerId;
	
	private String diagRepId;;
	
	private String description;
	
	private int estDuration;
	
	private boolean onSite;
	
	private BigDecimal cost;

	public int getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(int engineerId) {
		this.engineerId = engineerId;
	}

	public String getDiagRepId() {
		return diagRepId;
	}

	public void setDiagRepId(String diagRepId) {
		this.diagRepId = diagRepId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEstDuration() {
		return estDuration;
	}

	public void setEstDuration(int estDuration) {
		this.estDuration = estDuration;
	}

	public boolean isOnSite() {
		return onSite;
	}

	public void setOnSite(boolean onSite) {
		this.onSite = onSite;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	
}
