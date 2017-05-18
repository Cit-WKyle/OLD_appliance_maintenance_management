package com.kyle.ie.maintenance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.maintenance.models.MaintenanceOrganisation;
import com.kyle.ie.maintenance.repositories.IMaintenanceOrganisationRepository;

@Service
public class MaintenanceOrganisationService implements IMaintenanceOrganisationService {
	
	@Autowired
	private IMaintenanceOrganisationRepository _mainOrgRepo;

	@Override
	public void deleteDiagnosticReportPayload(String orgId, String reportId) {
		MaintenanceOrganisation org = _mainOrgRepo.findOne(orgId);
		org.getPendingDiagnosticReports().remove(reportId);
		_mainOrgRepo.save(org);
	}

	@Override
	public void reopenDiagnosticReport(String orgId, String reportId) {
		MaintenanceOrganisation org = _mainOrgRepo.findOne(orgId);
		org.getRespondedDiagnosticReports().remove(reportId);
		org.getPendingDiagnosticReports().add(reportId);
		_mainOrgRepo.save(org);
		
	}

	@Override
	public void addEngineerToOrganisation(String orgId, int accountId) {
		MaintenanceOrganisation org = _mainOrgRepo.findOne(orgId);
		org.getEngineers().add(accountId);
		_mainOrgRepo.save(org);
		
	}

	@Override
	public boolean respondToDiagnosticReport(int engineerId, String reportId) {
		for(MaintenanceOrganisation org: _mainOrgRepo.findAll()) {
			for(int engineer: org.getEngineers()) {
				if(engineer == engineerId) {
					org.getPendingDiagnosticReports().remove(reportId);
					org.getRespondedDiagnosticReports().add(reportId);
					_mainOrgRepo.save(org);
					return true;
				}
			}
		}
		return false;
	}

}
