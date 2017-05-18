package com.kyle.ie.property.controller;

import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.property.data.IPropertyDAO;
import com.kyle.ie.property.model.Property;

import java.util.List;

/**
 * Created by kyle_williamson on 12/02/2017.
 */

public interface IPropertyController {

    void getPropertiesForManager(IJwtToken token, ICallback<List<String>> callback);

    IPropertyDAO getDAO();
}
