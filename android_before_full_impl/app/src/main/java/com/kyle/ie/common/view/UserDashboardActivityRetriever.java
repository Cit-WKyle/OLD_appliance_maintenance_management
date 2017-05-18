package com.kyle.ie.common.view;

import com.kyle.ie.manager.view.PropertyManagerDashboardActivity;

/**
 * Created by kyle_williamson on 11/02/2017.
 */

public class UserDashboardActivityRetriever implements IUserDashboardActivityRetriever {

    private static final String PROPERTY_MANAGER_TYPE = "property_manager";

    @Override
    public Class getDashboardActivityForUserType(String userType) {
        switch(userType) {
            case PROPERTY_MANAGER_TYPE:
                return PropertyManagerDashboardActivity.class;
        }
        throw new InvalidUserTypeException();
    }
}
