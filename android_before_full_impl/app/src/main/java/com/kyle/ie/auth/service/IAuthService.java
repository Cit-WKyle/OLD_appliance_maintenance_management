package com.kyle.ie.auth.service;

import com.kyle.ie.auth.form.ILoginForm;
import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.auth.model.UserContext;
import com.kyle.ie.common.callback.ICallback;

/**
 * Created by Kyle on 18/01/2017.
 */
public interface IAuthService {

    void postLogin(ILoginForm loginForm, ICallback<IJwtToken> callback);

    void getUserContext(IJwtToken token, ICallback<UserContext> callback);
}
