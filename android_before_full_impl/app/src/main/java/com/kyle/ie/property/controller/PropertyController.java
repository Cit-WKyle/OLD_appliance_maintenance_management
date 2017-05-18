package com.kyle.ie.property.controller;

import android.telecom.Call;

import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.common.callback.CallbackResult;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.service.ServiceFactory;
import com.kyle.ie.property.data.IPropertyDAO;
import com.kyle.ie.property.data.ListPropertyDAO;
import com.kyle.ie.property.model.Property;
import com.kyle.ie.property.service.IPropertyService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyle_williamson on 12/02/2017.
 */

public class PropertyController implements IPropertyController {

    private IPropertyDAO _properties;

    private IPropertyService _propService;

    public PropertyController() {
        _propService = ServiceFactory.getInstance().createPropertyService();
    }

    @Override
    public void getPropertiesForManager(IJwtToken token, final ICallback<List<String>> callback) {
        _propService.getPropertiesForManager(token, new ICallback<List<Property>>() {
            @Override
            public void callback(Payload<List<Property>> payload) {
                if(payload.isSuccessful()) {
                    _properties = new ListPropertyDAO(payload.getData());
                    Payload pload = new Payload(CallbackResult.SUCCESS);
                    pload.setData(CallbackResult.SUCCESS);
                    callback.callback(pload);
                } else {
                    _properties = new ListPropertyDAO(new ArrayList<Property>());
                    Payload error = new Payload(CallbackResult.ERROR);
                    error.setError(payload.getError());
                    callback.callback(error);
                }
            }
        });
    }

    @Override
    public IPropertyDAO getDAO() {
        return _properties;
    }
}
