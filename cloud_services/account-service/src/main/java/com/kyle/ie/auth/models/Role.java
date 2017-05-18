package com.kyle.ie.auth.models;

public enum Role {
	ADMIN, MEMBER;
	
    public String authority() {
        return "ROLE_" + this.name();
    }
}
