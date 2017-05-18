package com.kyle.ie.common.service;

import com.kyle.ie.auth.service.IAuthService;
import com.kyle.ie.property.service.IPropertyService;
import com.kyle.ie.property_appliance.service.IPropertyApplianceService;

/**
 * Created by Kyle on 18/01/2017.
 */
public interface IServiceFactory {

    IAuthService createAuthService();

    IPropertyService createPropertyService();

    IPropertyApplianceService createPropertyApplianceService();
}
