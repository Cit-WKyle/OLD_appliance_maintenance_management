package com.kyle.ie.common.controller;

import com.kyle.ie.auth.controller.IAuthController;
import com.kyle.ie.property.controller.IPropertyController;
import com.kyle.ie.property_appliance.controller.IPropertyApplianceController;

/**
 * Created by Kyle on 18/01/2017.
 */
public interface IControllerFactory {

    IAuthController createAuthController();

    IPropertyController createPropertyController();

    IPropertyApplianceController createPropertyApplianceController();
}
