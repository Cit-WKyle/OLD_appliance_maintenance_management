package com.kyle.ie.common.web;

/**
 * Created by Kyle on 18/01/2017.
 */
public interface IWebClientFactory {

    IRestClient getAuthRestClient();

    IRestClient getPropertyRestClient();

    IRestClient getPropertyApplianceRestClient();
}
