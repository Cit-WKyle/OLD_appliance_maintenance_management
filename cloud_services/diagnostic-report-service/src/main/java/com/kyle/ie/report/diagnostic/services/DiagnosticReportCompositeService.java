package com.kyle.ie.report.diagnostic.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.ie.report.diagnostic.clients.http.IPropertyApplianceClient;
import com.kyle.ie.report.diagnostic.models.DiagnosticReport;
import com.kyle.ie.report.diagnostic.models.DiagnosticReportComposite;

@Service
public class DiagnosticReportCompositeService implements IDiagnosticReportCompositeService {
	
	@Autowired
	private IPropertyApplianceClient _pApplClient;

	@Override
	public DiagnosticReportComposite compose(DiagnosticReport report, String token) {
		DiagnosticReportComposite diagRepoComp = new DiagnosticReportComposite(report);
		diagRepoComp.setPropAppl(_pApplClient.getPropertyAppliance(token, report.getPropApplId()));
		return diagRepoComp;
	}

	@Override
	public List<DiagnosticReportComposite> compose(List<DiagnosticReport> reports, String token) {
		List<DiagnosticReportComposite> compList = new ArrayList<>();
		for(DiagnosticReport report: reports) {
			compList.add(this.compose(report, token));
		}
		return compList;
	}

}
