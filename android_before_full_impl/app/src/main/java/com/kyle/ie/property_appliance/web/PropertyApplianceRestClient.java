package com.kyle.ie.property_appliance.web;

import com.kyle.ie.MainActivity;
import com.kyle.ie.common.web.IRestClient;
import com.kyle.ie.common.web.IWebConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ContentType;

/**
 * Created by kyle_williamson on 16/02/2017.
 */

public class PropertyApplianceRestClient implements IRestClient {

    private final static String PROPERTY_APPLIANCE_RESOURCE = "/property-appliance";

    private AsyncHttpClient _client;

    public PropertyApplianceRestClient(AsyncHttpClient client) {
        _client = client;
    }

    private String getAbsoluteUrl(String resource) {
        //return IWebConstants.API_URL + PROPERTY_APPLIANCE_RESOURCE + resource;
        return MainActivity.getInstance().EDGE_IP + PROPERTY_APPLIANCE_RESOURCE + resource;
    }

    @Override
    public void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        _client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    @Override
    public void post(String url, HttpEntity entity, JsonHttpResponseHandler responseHandler) {
        _client.post(MainActivity.getInstance().getApplicationContext(), getAbsoluteUrl(url), entity, ContentType.APPLICATION_JSON.getMimeType(), responseHandler);
    }

    @Override
    public AsyncHttpClient getClient() {
        return _client;
    }
}
