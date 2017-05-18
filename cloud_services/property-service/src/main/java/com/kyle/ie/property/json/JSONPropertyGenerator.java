package com.kyle.ie.property.json;

import org.json.JSONObject;

import com.kyle.ie.property.models.Property;

public class JSONPropertyGenerator {
	
	private static final String PROP_ID_KEY = "propId";
	private static final String ADDR_KEY = "address";
	private static final String AGE_KEY = "age";
	private static final String BATHCNT_KEY = "bathroomCount";
	private static final String BEDCNT_KEY = "bedCount";
	private static final String MNGER_ID_KEY = "managerId";
	private static final String PREV_MNG_KEY = "prevManagers";
	private static final String PREV_TNT_KEY = "prevTennants";
	private static final String CUR_TNT_KEY = "currTennants";
	private static final String PREV_APPL_KEY = "prevAppliances";

	public JSONObject generate(Property prop) {
		JSONObject obj = new JSONObject();
		obj.put(PROP_ID_KEY, prop.getPropId());
		obj.put(ADDR_KEY, prop.getAddress());
		obj.put(AGE_KEY, prop.getAge());
		obj.put(BATHCNT_KEY, prop.getBathroomCount());
		obj.put(BEDCNT_KEY, prop.getBedCount());
		obj.put(MNGER_ID_KEY, prop.getManagerId());
		obj.put(PREV_MNG_KEY, prop.getPrevManagers());
		obj.put(PREV_TNT_KEY, prop.getPrevTennants());
		obj.put(CUR_TNT_KEY, prop.getCurrTennants());
		obj.put(PREV_APPL_KEY, prop.getPrevAppliances());
		
		return obj;
	}
}
