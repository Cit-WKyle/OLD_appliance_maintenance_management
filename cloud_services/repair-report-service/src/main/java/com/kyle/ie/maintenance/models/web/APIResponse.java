package com.kyle.ie.maintenance.models.web;

public class APIResponse {

    private String status;
    private String message;
 
    public APIResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
 
    public String getStatus() {
        return status;
    }
 
    public String getMessage() {
        return message;
    }
}
