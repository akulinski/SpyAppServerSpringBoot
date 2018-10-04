/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.response.stalkerresponses;

import com.spyapp.server.requestresponsemodel.response.GenericResponse;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class StalkerAddedResponse extends GenericResponse {

    public StalkerAddedResponse(String message, HttpStatus code, Date date) {
        super(message, code, date);
    }
}
