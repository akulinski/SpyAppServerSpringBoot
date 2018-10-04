/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.request;

public class VictimRequest {

    private String stalkerName;
    private String victimName;

    public String getStalkerName() {
        return stalkerName;
    }

    public void setStalkerName(String stalkerName) {
        this.stalkerName = stalkerName;
    }

    public String getVictimName() {
        return victimName;
    }

    public void setVictimName(String victimName) {
        this.victimName = victimName;
    }

    public VictimRequest(String stalkerName, String victimName) {
        this.stalkerName = stalkerName;
        this.victimName = victimName;
    }

    public VictimRequest() {

    }
}
