package com.kyle.ie.auth.service;

import com.kyle.ie.auth.form.ILoginForm;
import com.kyle.ie.auth.model.IJwtToken;
import com.kyle.ie.auth.model.JwtToken;
import com.kyle.ie.auth.model.UserContext;
import com.kyle.ie.common.callback.CallbackResult;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.web.IRestClient;
import com.kyle.ie.common.web.IWebConstants;
import com.kyle.ie.common.web.WebClientFactory;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Kyle on 18/01/2017.
 */
public class AuthService implements IAuthService {

    private static final String LOGIN_ROUTE = "/login";
    private static final String USER_ROUTE = "/user";

    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    private static final String TYPE_KEY = "type";

    private static final String TOKEN_KEY = "token";

    private IRestClient _authRestClient;

    public AuthService() {
        _authRestClient = WebClientFactory.getInstance().getAuthRestClient();
    }

    @Override
    public void postLogin(ILoginForm loginForm, final ICallback<IJwtToken> callback) {
        JSONObject params = new JSONObject();
        try {
            params.put(USERNAME_KEY, loginForm.getUsername());
            params.put(PASSWORD_KEY, loginForm.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity entity = null;
        try {
            entity = new StringEntity(params.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType()));

        _authRestClient.post(LOGIN_ROUTE, entity, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                IJwtToken token = null;
                try {
                    token = new JwtToken(response.getString(TOKEN_KEY));
                } catch (JSONException e) {
                    //TODO: Properly handle exceptions
                    e.printStackTrace();
                }
                Payload authPayload = new Payload<IJwtToken>(CallbackResult.SUCCESS);
                authPayload.setData(token);
                callback.callback(authPayload);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject response) {
                Payload authPayload = new Payload<IJwtToken>(CallbackResult.ERROR);
                System.out.println(response);
                try {
                    authPayload.setError(response.getString(IWebConstants.MESSAGE_KEY));
                } catch (JSONException e) {
                    //TODO: Properly handle exceptions
                    e.printStackTrace();
                }
                callback.callback(authPayload);
            }
        });
    }

    @Override
    public void getUserContext(IJwtToken token, final ICallback<UserContext> callback) {
        _authRestClient.getClient().addHeader(IWebConstants.X_AUTH_HEADER, IWebConstants.BEARER + token.getToken());
        _authRestClient.get(USER_ROUTE, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String username = "";
                String userType = "";

                try {
                    username = response.getString(USERNAME_KEY);
                    userType = response.getString(TYPE_KEY);
                } catch(JSONException e) {
                    //TODO: Properly handle exceptions
                    e.printStackTrace();
                }
                UserContext uCtxt = new UserContext(username, userType);
                Payload payload = new Payload<>(CallbackResult.SUCCESS);
                payload.setData(uCtxt);
                callback.callback(payload);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject response) {
                Payload payload = new Payload<>(CallbackResult.ERROR);
                System.out.println(response);
                try {
                    payload.setError(response.getString(IWebConstants.MESSAGE_KEY));
                } catch (JSONException e) {
                    //TODO: Properly handle exceptions
                    e.printStackTrace();
                }
                callback.callback(payload);
            }
        });
    }
}
