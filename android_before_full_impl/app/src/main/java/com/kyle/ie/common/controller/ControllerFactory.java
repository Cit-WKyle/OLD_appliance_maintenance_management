package com.kyle.ie.common.controller;

import com.kyle.ie.auth.controller.AuthController;
import com.kyle.ie.auth.controller.IAuthController;
import com.kyle.ie.property.controller.IPropertyController;
import com.kyle.ie.property.controller.PropertyController;
import com.kyle.ie.property_appliance.controller.IPropertyApplianceController;
import com.kyle.ie.property_appliance.controller.PropertyApplianceController;

/**
 * Created by Kyle on 18/01/2017.
 */
public class ControllerFactory implements IControllerFactory {

    private static IControllerFactory _instance;

    private IAuthController _authController;
    private IPropertyController _propertyController;
    private IPropertyApplianceController _propertyApplianceController;

    private ControllerFactory() {}

    public static IControllerFactory getInstance() {
        if(_instance == null) {
            _instance = new ControllerFactory();
        }
        return _instance;
    }

    @Override
    public IAuthController createAuthController() {
        if(_authController == null) {
            _authController = new AuthController();
        }
        return _authController;
    }

    @Override
    public IPropertyController createPropertyController() {
        if(_propertyController == null) {
            _propertyController = new PropertyController();
        }
        return _propertyController;
    }

    @Override
    public IPropertyApplianceController createPropertyApplianceController() {
        if(_propertyApplianceController == null) {
            _propertyApplianceController = new PropertyApplianceController();
        }
        return _propertyApplianceController;
    }
}
