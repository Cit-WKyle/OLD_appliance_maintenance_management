package com.kyle.ie.common.callback;

/**
 * Created by Kyle on 18/01/2017.
 */
public class CallbackException extends Exception {

    public CallbackException() {};

    public CallbackException(String error) {
        super(error);
    }
}
