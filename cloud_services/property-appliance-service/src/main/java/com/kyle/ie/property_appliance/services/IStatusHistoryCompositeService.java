package com.kyle.ie.property_appliance.services;

import java.util.List;

import com.kyle.ie.property_appliance.models.StatusHistory;
import com.kyle.ie.property_appliance.models.StatusHistoryComposite;

public interface IStatusHistoryCompositeService {

	List<StatusHistoryComposite> composeStatusHistoryList(List<StatusHistory> statusHistory);
}
