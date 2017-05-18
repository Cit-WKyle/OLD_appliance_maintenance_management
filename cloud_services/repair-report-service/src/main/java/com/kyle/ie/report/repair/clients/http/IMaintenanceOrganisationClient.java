package com.kyle.ie.report.repair.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyle.ie.maintenance.models.web.APIResponse;
import com.kyle.ie.maintenance.models.web.DiagReportResponsePayload;

@FeignClient(value="maintenance-organisation-service", name="maintenance-organisation-service")
public interface IMaintenanceOrganisationClient {
	
	@RequestMapping(value = "/diagnostic-report/respond", method=RequestMethod.POST)
	public @ResponseBody APIResponse respondToDiagnosticReport(@RequestParam String token, @RequestBody DiagReportResponsePayload payload);
}
