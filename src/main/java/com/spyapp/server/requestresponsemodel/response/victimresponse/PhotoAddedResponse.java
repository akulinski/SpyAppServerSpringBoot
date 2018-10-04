/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.response.victimresponse;

import com.spyapp.server.requestresponsemodel.response.GenericResponse;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class PhotoAddedResponse extends GenericResponse {

    public PhotoAddedResponse(String message, HttpStatus code, Date date) {
        super(message, code, date);
    }
}
