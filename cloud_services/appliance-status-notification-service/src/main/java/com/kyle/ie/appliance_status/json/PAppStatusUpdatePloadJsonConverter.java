package com.kyle.ie.appliance_status.json;

import com.kyle.ie.appliance_status.models.PropertyApplianceStatusUpdatePayload;

import io.vertx.core.json.JsonObject;

public class PAppStatusUpdatePloadJsonConverter {
	
	private static final String NEW_STATUS_KEY = "newStatusId";
	private static final String OLD_STATUS_KEY = "oldStatusId";
	private static final String PROP_APPL_ID_KEY = "propertyApplianceId";
	private static final String PROP_APPL_NAME_KEY = "propertyApplianceName";

	public PropertyApplianceStatusUpdatePayload convert(JsonObject json) {
		PropertyApplianceStatusUpdatePayload pload = new PropertyApplianceStatusUpdatePayload(); 
		pload.setNewStatusId(json.getString(NEW_STATUS_KEY));
		pload.setOldStatusId(json.getString(OLD_STATUS_KEY));
		pload.setPropertyApplianceId(json.getString(PROP_APPL_ID_KEY));
		pload.setPropertyApplianceName(json.getString(PROP_APPL_NAME_KEY));
		
		return pload;
	}
}
