/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.response;

import org.springframework.http.HttpStatus;

import java.util.Date;

public abstract class GenericResponse {

    private String message;

    private HttpStatus code;

    private Date date;

    public GenericResponse(String message, HttpStatus code, Date date) {
        this.message = message;
        this.code = code;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
