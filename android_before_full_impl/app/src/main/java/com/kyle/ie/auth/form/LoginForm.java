package com.kyle.ie.auth.form;

/**
 * Created by Kyle on 18/01/2017.
 */
public class LoginForm implements ILoginForm {

    private String _username;
    private String _password;

    public LoginForm(String username, String password) {
        _username = username;
        _password = password;
    }


    @Override
    public String getUsername() {
        return _username;
    }

    @Override
    public void setUsername(String username) {
        _username = username;
    }

    @Override
    public String getPassword() {
        return _password;
    }

    @Override
    public void setPassword(String password) {
        _password = password;
    }
}
