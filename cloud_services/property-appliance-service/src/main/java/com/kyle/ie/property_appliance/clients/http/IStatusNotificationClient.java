package com.kyle.ie.property_appliance.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kyle.ie.appliance_status.models.PropertyApplianceStatusUpdatePayload;

@FeignClient(url="${service.notification.url}", name="appliance-status-notification-service")
public interface IStatusNotificationClient {


	@RequestMapping(method = RequestMethod.POST, value = "/notification")
	void sendNotification(@RequestBody PropertyApplianceStatusUpdatePayload pload);
}
