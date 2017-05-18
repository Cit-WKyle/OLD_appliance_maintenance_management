package com.kyle.ie.maintenance.scheduling.controllers.rest;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.maintenance.scheduling.clients.http.IAuthClient;
import com.kyle.ie.maintenance.scheduling.forms.MaintenanceScheduleForm;
import com.kyle.ie.maintenance.scheduling.forms.ProposedTime;
import com.kyle.ie.maintenance.scheduling.services.IMaintenanceSchedulingService;

@RestController
@RequestMapping("/")
public class MaintenanceSchedulingRestController {
	
	private static final String AUTH_FAILED = "Authentication failed";
	
	private static final String BEARER = "Bearer ";
	
	@Autowired
	private IMaintenanceSchedulingService _schedServ;
	
	@Autowired
	private IAuthClient _authClient;
	
	@RequestMapping(value="/propose", method=RequestMethod.POST)
	public void proposeSchedules(@RequestParam String token, @RequestBody MaintenanceScheduleForm form) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			for(ProposedTime time: form.getProposedTimes()) {
				_schedServ.create(form.getRepairReportId(), time);
			}
		}
		
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value="/{id}/accept", method=RequestMethod.POST)
	public void acceptSchedule(@RequestParam String token, @PathVariable("id") String id) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			_schedServ.acceptSchedule(id);
		}
		
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value="/{id}/decline", method=RequestMethod.POST)
	public void declineSchedule(@RequestParam String token, @PathVariable("id") String id) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			_schedServ.declineSchedule(id);
		}
		
		throw new AuthException(AUTH_FAILED);
	}
}
