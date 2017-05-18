package com.kyle.ie.auth.web;

import com.kyle.ie.MainActivity;
import com.kyle.ie.common.web.IRestClient;
import com.kyle.ie.common.web.IWebConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ContentType;

/**
 * Created by Kyle on 18/01/2017.
 */
public class AuthRestClient implements IRestClient {
    private static final String ACCOUNT_RESOURCE = "/account";

    private AsyncHttpClient _client;

    public AuthRestClient(AsyncHttpClient client) {
        _client = client;
    }

    @Override
    public void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        _client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    @Override
    public void post(String url, HttpEntity entity, JsonHttpResponseHandler responseHandler) {
        _client.post(MainActivity.getInstance().getApplicationContext(), getAbsoluteUrl(url), entity, ContentType.APPLICATION_JSON.getMimeType(), responseHandler);
    }

    private String getAbsoluteUrl(String resource) {
        return MainActivity.getInstance().EDGE_IP + ACCOUNT_RESOURCE + resource;
    }

    public AsyncHttpClient getClient() {
        return _client;
    }
}
