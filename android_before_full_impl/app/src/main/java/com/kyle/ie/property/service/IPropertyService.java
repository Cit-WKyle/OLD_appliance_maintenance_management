package com.kyle.ie.property.service;

import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.property.model.Property;

import java.util.List;

/**
 * Created by kyle_williamson on 12/02/2017.
 */

public interface IPropertyService {

    void getPropertiesForManager(IJwtToken token, final ICallback<List<Property>> callback);
}
