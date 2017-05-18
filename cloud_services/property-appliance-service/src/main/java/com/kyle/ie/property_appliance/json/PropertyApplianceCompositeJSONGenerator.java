package com.kyle.ie.property_appliance.json;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kyle.ie.property_appliance.models.PropertyApplianceComposite;

public class PropertyApplianceCompositeJSONGenerator {
	
	private static final String ID_KEY = "id";
	private static final String APPL_KEY = "appliance";
	private static final String STATUS_KEY = "status";
	private static final String NAME_KEY = "name";
	private static final String AGE_KEY = "age";
	private static final String STAT_HIST_KEY = "statusHistory";

	public JSONObject generate(PropertyApplianceComposite propAppl) {
		JSONObject obj = new JSONObject();
		obj.put(ID_KEY, propAppl.getId());
		obj.put(APPL_KEY, new JSONObject(propAppl.getAppliance()));
		obj.put(STATUS_KEY, new JSONObject(propAppl.getStatus()));
		obj.put(NAME_KEY, propAppl.getName());
		obj.put(AGE_KEY, propAppl.getAge());
		obj.put(STAT_HIST_KEY, new JSONArray(propAppl.getStatusHistory()));
		return obj;
	}
	
}
