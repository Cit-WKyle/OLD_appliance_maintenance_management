package com.kyle.ie.property_appliance.controller;

import android.telecom.Call;

import com.kyle.ie.auth.model.JwtToken;
import com.kyle.ie.auth.persistence.IAuthPersistenceConstants;
import com.kyle.ie.common.callback.CallbackResult;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.persistence.IPersistenceHandler;
import com.kyle.ie.common.persistence.PersistenceHandler;
import com.kyle.ie.common.service.ServiceFactory;
import com.kyle.ie.property.model.Property;
import com.kyle.ie.property_appliance.data.IPropertyApplianceDAO;
import com.kyle.ie.property_appliance.data.ListPropertyApplianceDAO;
import com.kyle.ie.property_appliance.model.PropertyAppliance;
import com.kyle.ie.property_appliance.service.IPropertyApplianceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyle_williamson on 16/02/2017.
 */

public class PropertyApplianceController implements IPropertyApplianceController {

    private IPropertyApplianceService _propApplService;
    private IPersistenceHandler _persHandler;
    private IPropertyApplianceDAO _propertyAppliances;

    public PropertyApplianceController() {
        _propApplService = ServiceFactory.getInstance().createPropertyApplianceService();
        _persHandler = new PersistenceHandler();
    }

    @Override
    public void getProperyAppliancesForProperty(Property property, final ICallback<List<String>> callback) {
        String token = _persHandler.getSettings(IAuthPersistenceConstants.AUTH_PREFS_NAME).getString(IAuthPersistenceConstants.JWT_TOKEN_KEY, "");

        _propApplService.getPropertyAppliancesForProperty(new JwtToken(token), property.getId(), new ICallback<List<PropertyAppliance>>() {
            @Override
            public void callback(Payload<List<PropertyAppliance>> payload) {
                if(payload.isSuccessful()) {
                    _propertyAppliances = new ListPropertyApplianceDAO(payload.getData());
                    Payload pload = new Payload(CallbackResult.SUCCESS);
                    pload.setData(pload);
                    callback.callback(pload);
                } else {
                    _propertyAppliances = new ListPropertyApplianceDAO(new ArrayList<PropertyAppliance>());
                    Payload error = new Payload(CallbackResult.ERROR);
                    error.setError(payload.getError());
                    callback.callback(error);
                }
            }
        });

    }

    @Override
    public IPropertyApplianceDAO getDAO() {
        return _propertyAppliances;
    }
}
