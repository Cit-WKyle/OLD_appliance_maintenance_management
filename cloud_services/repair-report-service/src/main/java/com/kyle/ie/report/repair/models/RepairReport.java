package com.kyle.ie.report.repair.models;

import java.math.BigDecimal;

import com.kyle.ie.report.repair.models.constants.ReportStatus;

public class RepairReport {

	private String id;
	
	private int engineerId;
	
	private String diagRepId;
	
	private ReportStatus repStatus;
	
	private String description;
	
	private int estDuration; //hours
	
	private boolean onSite;
	
	private BigDecimal cost;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public ReportStatus getRepStatus() {
		return repStatus;
	}

	public void setRepStatus(ReportStatus repStatus) {
		this.repStatus = repStatus;
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
