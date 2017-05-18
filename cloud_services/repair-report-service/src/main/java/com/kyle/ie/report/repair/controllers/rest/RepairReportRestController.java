package com.kyle.ie.report.repair.controllers.rest;

import javax.security.auth.message.AuthException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.maintenance.models.web.APIResponse;
import com.kyle.ie.maintenance.models.web.DiagReportResponsePayload;
import com.kyle.ie.report.repair.clients.http.IAuthClient;
import com.kyle.ie.report.repair.clients.http.IMaintenanceOrganisationClient;
import com.kyle.ie.report.repair.forms.RepairReportForm;
import com.kyle.ie.report.repair.json.JSONErrorGenerator;
import com.kyle.ie.report.repair.models.RepairReport;
import com.kyle.ie.report.repair.services.IRepairReportService;
import com.kyle.ie.report.repair.validators.IRepairReportFormValidator;

@RestController
@RequestMapping("/")
public class RepairReportRestController {
	
	private static final String AUTH_FAILED = "Authentication failed";
	
	private static final String SUCCESS = "Success";
	
	@Autowired
	private IRepairReportService _repService;
	
	@Autowired
	private IRepairReportFormValidator _repFormVal;
	
	@Autowired
	private IAuthClient _authClient;
	
	@Autowired
	private IMaintenanceOrganisationClient _mainOrgClient;

	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public String create(@RequestParam String token, @RequestBody RepairReportForm form) throws AuthException {
		
		AuthUserPayload pload = _authClient.auth(token);
		
		if(pload.getSuccess()) {
			boolean valid = _repFormVal.validate(form);
			if(!valid) {
				return new JSONErrorGenerator().generate(_repFormVal.getErrors()).toString();
			}
			APIResponse resp = _mainOrgClient.respondToDiagnosticReport(token, new DiagReportResponsePayload(form.getEngineerId(), form.getDiagRepId()));
			if(resp.getStatus().equals("ERROR")) {
				return new JSONObject(resp).toString();
			}
			RepairReport rep = _repService.create(form);
			return new JSONObject(rep).toString();
		}
		
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/decline", method=RequestMethod.POST)
	public APIResponse declineReport(@RequestParam String token, @PathVariable("id") String id) throws AuthException {
		
		AuthUserPayload pload = _authClient.auth(token);
		
		if(pload.getSuccess()) {
			_repService.declineReport(id);
			return new APIResponse(SUCCESS, SUCCESS);
		}
		
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/{id}/accept", method=RequestMethod.POST)
	public APIResponse acceptReport(@RequestParam String token, @PathVariable("id") String id) throws AuthException {
		
		AuthUserPayload pload = _authClient.auth(token);
		
		if(pload.getSuccess()) {
			_repService.acceptReport(id);
			return new APIResponse(SUCCESS, SUCCESS);
		}
		
		throw new AuthException(AUTH_FAILED);
	}
}
