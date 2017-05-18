package com.kyle.ie.auth.model;

/**
 * Created by Kyle on 21/01/2017.
 */
public class JwtToken  implements IJwtToken {

    private String _token;

    public JwtToken(String token) {
        _token = token;
    }

    @Override
    public String getToken() {
        return _token;
    }
}
