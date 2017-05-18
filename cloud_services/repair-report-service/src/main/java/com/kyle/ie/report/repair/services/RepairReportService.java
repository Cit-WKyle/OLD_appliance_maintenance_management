package com.kyle.ie.report.repair.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kyle.ie.report.repair.forms.RepairReportForm;
import com.kyle.ie.report.repair.models.RepairReport;
import com.kyle.ie.report.repair.models.constants.ReportStatus;
import com.kyle.ie.report.repair.repositories.IRepairReportRepository;

@Service
public class RepairReportService implements IRepairReportService {
	
	@Autowired
	private IRepairReportRepository _repo;

	@Override
	public void declineReport(String id) {
		RepairReport rep = _repo.findOne(id);
		rep.setRepStatus(ReportStatus.DECLINED);
	}

	@Override
	public void acceptReport(String id) {
		RepairReport report = _repo.findOne(id);
		String diagRepId = report.getDiagRepId();
		List<RepairReport> reports = Lists.newArrayList(_repo.findAll());
		for(RepairReport item: reports) {
			if(item.getDiagRepId().equals(diagRepId)) {
				item.setRepStatus(ReportStatus.DECLINED);
			}
		}
		report.setRepStatus(ReportStatus.ACCEPTED);
		
		_repo.save(reports);
		_repo.save(report);
	}

	@Override
	public RepairReport create(RepairReportForm form) {
		RepairReport rep = new RepairReport();
		rep.setCost(form.getCost());
		rep.setDescription(form.getDescription());
		rep.setDiagRepId(rep.getDiagRepId());
		rep.setEngineerId(form.getEngineerId());
		rep.setEstDuration(form.getEstDuration());
		rep.setOnSite(form.isOnSite());
		rep.setRepStatus(ReportStatus.DECLINED);
		_repo.save(rep);
		return rep;
	}

}
