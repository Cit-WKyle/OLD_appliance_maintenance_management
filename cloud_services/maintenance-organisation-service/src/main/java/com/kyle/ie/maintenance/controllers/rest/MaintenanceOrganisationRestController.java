package com.kyle.ie.maintenance.controllers.rest;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.maintenance.clients.http.IAuthClient;
import com.kyle.ie.maintenance.models.web.APIResponse;
import com.kyle.ie.maintenance.models.web.DiagReportPayload;
import com.kyle.ie.maintenance.models.web.DiagReportResponsePayload;
import com.kyle.ie.maintenance.models.web.EngineerPayload;
import com.kyle.ie.maintenance.services.IMaintenanceOrganisationService;

@RestController
@RequestMapping("/")
public class MaintenanceOrganisationRestController {
	
	private static final String AUTH_FAILED = "Authentication failed";
	
	private static final String BEARER = "Bearer ";
	
	private static final String SUCCESS = "Success";
	private static final String ERROR_STAT = "Error";
	private static final String ERROR_RESPOND_MSG = "Couldn't respond to diagnostic report";
	
	
	@Autowired
	private IAuthClient _authClient;
	
	@Autowired
	private IMaintenanceOrganisationService _mainOrgServ;
	
	@RequestMapping(value = "/diagnostic-report/delete", method=RequestMethod.POST)
	public @ResponseBody APIResponse deletePendingDiagnosticReport(@RequestParam String token, @RequestBody DiagReportPayload payload) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			_mainOrgServ.deleteDiagnosticReportPayload(payload.getOrgId(), payload.getDiagRepId());
			return new APIResponse (SUCCESS, SUCCESS);
		}
		
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/diagnostic-report/reopen", method=RequestMethod.POST)
	public @ResponseBody APIResponse reopenDiagnosticReport(@RequestParam String token, @RequestBody DiagReportPayload payload) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			_mainOrgServ.reopenDiagnosticReport(payload.getOrgId(), payload.getDiagRepId());
			return new APIResponse (SUCCESS, SUCCESS);
		}
		
		throw new AuthException(AUTH_FAILED);
	}
	
	@RequestMapping(value = "/diagnostic-report/respond", method=RequestMethod.POST)
	public @ResponseBody APIResponse respondToDiagnosticReport(@RequestParam String token, @RequestBody DiagReportResponsePayload payload) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			boolean success = _mainOrgServ.respondToDiagnosticReport(payload.getEngineerId(), payload.getDiagRepId());
			if(success) {
				return new APIResponse(SUCCESS, SUCCESS);
			} else {
				return new APIResponse(ERROR_STAT, ERROR_RESPOND_MSG);
			}
		}
		
		throw new AuthException(AUTH_FAILED);
	}
	
	@RequestMapping(value = "/engineer/add", method=RequestMethod.POST)
	public @ResponseBody APIResponse addEngineerToOrganisation(@RequestParam String token, @RequestBody EngineerPayload payload) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			_mainOrgServ.addEngineerToOrganisation(payload.getOrgId(), payload.getEngineerId());
			return new APIResponse (SUCCESS, SUCCESS);
		}
		
		throw new AuthException(AUTH_FAILED);
	}

}
