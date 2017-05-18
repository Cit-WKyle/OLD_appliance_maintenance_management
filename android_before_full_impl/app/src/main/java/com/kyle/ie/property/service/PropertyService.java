package com.kyle.ie.property.service;

import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.common.callback.CallbackResult;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.data.IJSONConverter;
import com.kyle.ie.common.web.IRestClient;
import com.kyle.ie.common.web.IWebConstants;
import com.kyle.ie.common.web.WebClientFactory;
import com.kyle.ie.property.data.PropertyJsonConverter;
import com.kyle.ie.property.model.Property;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kyle_williamson on 12/02/2017.
 */

public class PropertyService implements IPropertyService {

    private static String MANAGER_RESOURCE = "/manager";

    private IRestClient _restClient;
    private IJSONConverter<Property> _propConverter;

    public PropertyService() {
        _restClient = WebClientFactory.getInstance().getPropertyRestClient();
        _propConverter = new PropertyJsonConverter();
    }

    @Override
    public void getPropertiesForManager(IJwtToken token, final ICallback<List<Property>> callback) {

        final RequestParams params = new RequestParams();
        params.put(IWebConstants.TOKEN_KEY, token.getToken());

        _restClient.get(MANAGER_RESOURCE, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                List<Property> list = new ArrayList<Property>();
                System.out.println(response);
                for(int i=0; i<response.length(); i++) {
                    try {
                        JSONObject json = (JSONObject) response.get(i);
                        list.add(_propConverter.convertJSON(json));
                    } catch (JSONException e) {
                        //TODO: Correctly handle exception
                        e.printStackTrace();
                    }
                }
                Payload<List<Property>> payload = new Payload<>(CallbackResult.SUCCESS);
                payload.setData(list);
                callback.callback(payload);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject response) {
                System.out.println(response);
                Payload<List<Property>> payload = new Payload<>(CallbackResult.ERROR);
                try {
                    payload.setError(response.getString(IWebConstants.MESSAGE_KEY));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.callback(payload);
            }
        });
    }
}