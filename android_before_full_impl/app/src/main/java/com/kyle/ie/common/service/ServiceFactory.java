package com.kyle.ie.common.service;

import com.kyle.ie.auth.service.AuthService;
import com.kyle.ie.auth.service.IAuthService;
import com.kyle.ie.property.service.IPropertyService;
import com.kyle.ie.property.service.PropertyService;
import com.kyle.ie.property_appliance.service.IPropertyApplianceService;
import com.kyle.ie.property_appliance.service.PropertyApplianceService;

/**
 * Created by Kyle on 18/01/2017.
 */
public class ServiceFactory implements IServiceFactory {

    private static IServiceFactory _instance;

    private IAuthService _authService;
    private IPropertyService _propService;
    private IPropertyApplianceService _propApplService;

    private ServiceFactory() {}

    public static IServiceFactory getInstance() {
        if(_instance == null) {
            _instance = new ServiceFactory();
        }
        return _instance;
    }

    @Override
    public IAuthService createAuthService() {
        if(_authService == null) {
            _authService = new AuthService();
        }
        return _authService;
    }

    @Override
    public IPropertyService createPropertyService() {
        if(_propService == null) {
            _propService = new PropertyService();
        }
        return _propService;
    }

    @Override
    public IPropertyApplianceService createPropertyApplianceService() {
        if (_propApplService == null) {
            _propApplService = new PropertyApplianceService();
        }
        return _propApplService;
    }
}
