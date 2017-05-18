package com.kyle.ie.common.web;

import com.kyle.ie.auth.web.AuthRestClient;
import com.kyle.ie.property.web.PropertyRestClient;
import com.kyle.ie.property_appliance.web.PropertyApplianceRestClient;
import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Kyle on 18/01/2017.
 */
public class WebClientFactory implements IWebClientFactory {

    private static IWebClientFactory _webClientFactory;

    private static AsyncHttpClient _client = new AsyncHttpClient();

    private static IRestClient _authClient;
    private static IRestClient _propertyClient;
    private static IRestClient _propertyApplianceClient;

    private WebClientFactory() {}

    public static IWebClientFactory getInstance() {
        if(_webClientFactory == null) {
            _webClientFactory = new WebClientFactory();
        }
        return _webClientFactory;
    }

    @Override
    public IRestClient getAuthRestClient() {
        if(_authClient == null) {
            _authClient = new AuthRestClient(_client);
        }
        return _authClient;
    }

    @Override
    public IRestClient getPropertyRestClient() {
        if(_propertyClient == null) {
            _propertyClient = new PropertyRestClient(_client);
        }
        return _propertyClient;
    }

    @Override
    public IRestClient getPropertyApplianceRestClient() {
        if(_propertyApplianceClient == null) {
            _propertyApplianceClient = new PropertyApplianceRestClient(_client);
        }
        return _propertyApplianceClient;
    }
}
