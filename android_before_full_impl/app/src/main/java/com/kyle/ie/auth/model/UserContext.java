package com.kyle.ie.auth.model;

/**
 * Created by kyle_williamson on 11/02/2017.
 */

public class UserContext {

    private String _username;
    private String _userType;

    public UserContext(String username ,String userType) {
        _username = username;
        _userType = userType;
    }

    public String getUsername() {
        return _username;
    }

    public String getUserType() {
        return _userType;
    }
}
