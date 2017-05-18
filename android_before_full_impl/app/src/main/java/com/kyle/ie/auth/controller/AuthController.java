package com.kyle.ie.auth.controller;

import android.content.SharedPreferences;

import com.kyle.ie.auth.form.ILoginForm;
import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.auth.model.UserContext;
import com.kyle.ie.auth.persistence.IAuthPersistenceConstants;
import com.kyle.ie.auth.service.IAuthService;
import com.kyle.ie.common.callback.CallbackResult;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.persistence.IPersistenceHandler;
import com.kyle.ie.common.persistence.PersistenceHandler;
import com.kyle.ie.common.service.ServiceFactory;

/**
 * Created by Kyle on 18/01/2017.
 */
public class AuthController implements IAuthController {

    private IAuthService _authService;
    private IPersistenceHandler _persistenceHandler;

    public AuthController() {
        _authService = ServiceFactory.getInstance().createAuthService();
        _persistenceHandler = new PersistenceHandler();
    }

    @Override
    public void login(ILoginForm form, final ICallback<String> callback) {
        _authService.postLogin(form, new ICallback<IJwtToken>() {

            @Override
            public void callback(Payload<IJwtToken> payload) {
                if(payload.isSuccessful()) {
                    final SharedPreferences.Editor editor= _persistenceHandler.getEditor(IAuthPersistenceConstants.AUTH_PREFS_NAME);
                    editor.putString(IAuthPersistenceConstants.JWT_TOKEN_KEY, payload.getData().getToken());
                    AuthController.this.getUserContext(payload.getData(), new ICallback<UserContext>() {

                        @Override
                        public void callback(Payload<UserContext> payload) {
                            if(payload.isSuccessful()) {
                                editor.putString(IAuthPersistenceConstants.USERNAME_KEY, payload.getData().getUsername());
                                editor.putString(IAuthPersistenceConstants.USERTYPE_KEY, payload.getData().getUserType());
                                editor.commit();
                                Payload success = new Payload<>(CallbackResult.SUCCESS);
                                success.setData(payload.getData().getUserType());
                                callback.callback(success);
                            } else {
                                final Payload error = new Payload<>(CallbackResult.ERROR);
                                error.setError(payload.getError());
                                callback.callback(error);
                            }
                        }
                    });
                } else {
                    final Payload error = new Payload<>(CallbackResult.ERROR);
                    error.setError(payload.getError());
                    callback.callback(error);
                }
            }
        });
    }

    @Override
    public void getUserContext(IJwtToken token, final ICallback<UserContext> callback) {
        _authService.getUserContext(token, new ICallback<UserContext>() {
            @Override
            public void callback(Payload<UserContext> payload) {
                callback.callback(payload);
            }
        });
    }
}
