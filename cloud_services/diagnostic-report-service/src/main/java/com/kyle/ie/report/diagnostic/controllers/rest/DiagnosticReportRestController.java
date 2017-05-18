package com.kyle.ie.report.diagnostic.controllers.rest; 

import java.util.List;

import javax.security.auth.message.AuthException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.report.diagnostic.clients.http.IAuthClient;
import com.kyle.ie.report.diagnostic.form.DiagnosticReportForm;
import com.kyle.ie.report.diagnostic.json.JSONErrorGenerator;
import com.kyle.ie.report.diagnostic.models.DiagnosticReportComposite;
import com.kyle.ie.report.diagnostic.services.IDiagnosticReportCompositeService;
import com.kyle.ie.report.diagnostic.services.IDiagnosticReportService;
import com.kyle.ie.report.diagnostic.validators.IDiagnosticReportValidator;

@RestController
@RequestMapping("/")
public class DiagnosticReportRestController {
	
	private static final String AUTH_FAILED = "Authentication failed";
	
	private static final String BEARER = "Bearer ";
	
	@Autowired
	private IAuthClient _authClient;
	
	@Autowired
	private IDiagnosticReportCompositeService _diagRepCompService;
	
	@Autowired
	private IDiagnosticReportService _diagRepService;
	
	@Autowired
	private IDiagnosticReportValidator _diagRepValidator;

	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public @ResponseBody String generateReport(@RequestParam String token, @RequestBody DiagnosticReportForm form) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			boolean valid = _diagRepValidator.validate(form);
			if(!valid) {
				return new JSONErrorGenerator().generate(_diagRepValidator.getErrors()).toString();
			}
			
			return new JSONObject(_diagRepCompService.compose(_diagRepService.createFromForm(form), token)).toString();
		}
		
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/manager/{id}", method=RequestMethod.GET)
	public List<DiagnosticReportComposite> getForManager(@RequestParam String token, @PathVariable("id") int managerId) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			return _diagRepCompService.compose(_diagRepService.getForManagerId(managerId), token);
		}
		
		throw new AuthException(AUTH_FAILED);
	}

	@RequestMapping(value = "/organisation/{id}", method=RequestMethod.GET)
	public List<DiagnosticReportComposite> getForOrganisation(@RequestParam String token, @PathVariable("id") String orgId) throws AuthException {

		AuthUserPayload apl = _authClient.auth(BEARER + token);
		
		if(apl.getSuccess()) {
			return _diagRepCompService.compose(_diagRepService.getForOrganisationId(orgId), token);
		}
		
		throw new AuthException(AUTH_FAILED);
	}
}
