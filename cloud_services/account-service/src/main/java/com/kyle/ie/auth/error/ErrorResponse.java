package com.kyle.ie.auth.error;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private final HttpStatus _status;

    private final String _message;
    
    private final ErrorCode _errorCode;

    private final Date _timestamp;

    protected ErrorResponse(final String message, final ErrorCode errorCode, HttpStatus status) {
        _message = message;
        _errorCode = errorCode;
        _status = status;
        _timestamp = new java.util.Date();
    }

    public static ErrorResponse of(final String message, final ErrorCode errorCode, HttpStatus status) {
        return new ErrorResponse(message, errorCode, status);
    }

    public Integer getStatus() {
        return _status.value();
    }

    public String getMessage() {
        return _message;
    }

    public ErrorCode getErrorCode() {
        return _errorCode;
    }

    public Date getTimestamp() {
        return _timestamp;
    }
}
