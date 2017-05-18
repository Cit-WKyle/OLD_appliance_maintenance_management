package com.kyle.ie.property_appliance.json;

import java.util.List;

import org.json.JSONObject;

public class JSONErrorGenerator {
	
	private static final String MESSAGE_STR = "Invalid Property";
	
	private static final String STATUS_KEY = "status";
	private static final String MESSAGE_KEY = "message";
	private static final String ERRORS_KEY = "errors";
	
	public JSONObject generate(List<String> errors) {
		JSONObject obj = new JSONObject();
		obj.put(STATUS_KEY, 400);
		obj.put(MESSAGE_KEY, MESSAGE_STR);
		obj.put(ERRORS_KEY, errors);
		
		return obj;
	}
}
