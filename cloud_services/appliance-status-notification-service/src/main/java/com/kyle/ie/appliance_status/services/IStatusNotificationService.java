package com.kyle.ie.appliance_status.services;

import com.kyle.ie.appliance_status.models.PropertyApplianceStatusUpdatePayload;
import com.kyle.ie.appliance_status.models.StatusNotificationPayload;

public interface IStatusNotificationService {

	StatusNotificationPayload prepareNotification(PropertyApplianceStatusUpdatePayload pAppStatPload);
}
