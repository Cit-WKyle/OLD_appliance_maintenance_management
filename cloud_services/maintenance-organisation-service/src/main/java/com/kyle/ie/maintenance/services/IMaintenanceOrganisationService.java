package com.kyle.ie.maintenance.services;

public interface IMaintenanceOrganisationService {

	void deleteDiagnosticReportPayload(String orgId, String reportId);
	
	void reopenDiagnosticReport(String orgId, String reportId);
	
	void addEngineerToOrganisation(String orgId, int accountId);
	
	boolean respondToDiagnosticReport(int engineerId, String reportId);
}
