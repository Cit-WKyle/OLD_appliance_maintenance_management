package com.kyle.ie.property_appliance.service;

import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.common.callback.CallbackResult;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.web.IRestClient;
import com.kyle.ie.common.web.IWebConstants;
import com.kyle.ie.common.web.WebClientFactory;
import com.kyle.ie.property_appliance.model.PropertyAppliance;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kyle_williamson on 16/02/2017.
 */

public class PropertyApplianceService implements IPropertyApplianceService {

    private static final String PROPERTY_RESOURCE = "/property/";

    private IRestClient _restClient;

    public PropertyApplianceService() {
        _restClient = WebClientFactory.getInstance().getPropertyApplianceRestClient();
    }

    @Override
    public void getPropertyAppliancesForProperty(IJwtToken token, String propertyId, final ICallback<List<PropertyAppliance>> callback) {

        final RequestParams params = new RequestParams();
        params.put(IWebConstants.TOKEN_KEY, token.getToken());

        _restClient.get(PROPERTY_RESOURCE + propertyId, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println(response);
                List<PropertyAppliance> paList = new ArrayList<PropertyAppliance>();
                for(int i=0; i<response.length(); i++) {
                    try {
                        JSONObject json = (JSONObject) response.get(i);
                        paList.add(null);
                    } catch (JSONException e) {
                        //TODO: Correctly handle exception
                        e.printStackTrace();
                    }
                }
                Payload<List<PropertyAppliance>> payload = new Payload<>(CallbackResult.SUCCESS);
                payload.setData(new ArrayList<PropertyAppliance>());
                //payload.setData(paList); //TODO:
                callback.callback(payload);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                System.out.println(response);
                Payload<List<PropertyAppliance>> payload = new Payload<>(CallbackResult.ERROR);
                try {
                    payload.setError(response.getString(IWebConstants.MESSAGE_KEY));
                } catch(JSONException e) {
                    e.printStackTrace();
                }
                callback.callback(payload);
            }
        });
    }
}
