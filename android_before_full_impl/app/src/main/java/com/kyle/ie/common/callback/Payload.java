package com.kyle.ie.common.callback;

/**
 * Created by Kyle on 01/02/2017.
 */
public class Payload<T> {

    private static final String NOT_SUCCESSFUL_EXC = "Callback was not successful. No data to retrieve.";
    private static final String NOT_ERROR_EXC = "Callback was successful. No error to retrieved.";

    private T _data;
    private String _error;
    private CallbackResult _result;

    public Payload(CallbackResult result) {
        _result = result;
    }

    public boolean isSuccessful() {
        return (_result == CallbackResult.SUCCESS);
    }

    public T getData() throws PayloadException {
        if(_data == null) {
            throw new PayloadException(NOT_SUCCESSFUL_EXC);
        }
        return _data;
    }

    public String getError() throws PayloadException {
        if(_data != null) {
            throw new PayloadException(NOT_ERROR_EXC);
        }
        return _error;
    }

    public void setError(String err) {
        _error = err;
    }

    public void setData(T data) {
        _data = data;
    }
}
