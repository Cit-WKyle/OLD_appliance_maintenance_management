package com.kyle.ie.auth.controller;

import com.kyle.ie.auth.form.ILoginForm;
import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.auth.model.UserContext;
import com.kyle.ie.common.callback.ICallback;

/**
 * Created by Kyle on 18/01/2017.
 */
public interface IAuthController {

    void login(ILoginForm form, ICallback<String> callback);

    void getUserContext(IJwtToken token, ICallback<UserContext> callback);
}
