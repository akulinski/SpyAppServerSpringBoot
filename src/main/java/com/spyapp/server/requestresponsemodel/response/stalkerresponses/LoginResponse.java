/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.response.stalkerresponses;

import com.spyapp.server.requestresponsemodel.response.GenericResponse;

import java.util.Date;

public class LoginResponse {

    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LoginResponse(boolean success) {
        this.success = success;
    }
}
