package com.kyle.ie.property_appliance.service;

import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.property_appliance.model.PropertyAppliance;

import java.util.List;

/**
 * Created by kyle_williamson on 15/02/2017.
 */

public interface IPropertyApplianceService {

    void getPropertyAppliancesForProperty(IJwtToken token, String propertyId, ICallback<List<PropertyAppliance>> callback);
}
