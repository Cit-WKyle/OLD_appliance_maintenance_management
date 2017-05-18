package com.kyle.ie.auth.error;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    GLOBAL(2),

    AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11);
    
    private int _errorCode;

    private ErrorCode(int errorCode) {
        _errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return _errorCode;
    }
}
