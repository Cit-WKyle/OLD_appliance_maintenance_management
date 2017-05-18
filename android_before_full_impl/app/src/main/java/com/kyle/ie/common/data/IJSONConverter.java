package com.kyle.ie.common.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kyle_williamson on 13/02/2017.
 */

public interface IJSONConverter<T> {

    T convertJSON(JSONObject json) throws JSONException;
}
