/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.request;

public class LoginRequest {

    private String username;

    private String password;

    private String sessionid;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public LoginRequest(String username, String password, String sessionid) {
        this.username = username;
        this.password = password;
        this.sessionid = sessionid;
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequest() {

    }
}
