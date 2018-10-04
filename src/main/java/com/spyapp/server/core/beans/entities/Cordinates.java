package com.spyapp.server.core.beans.entities;

public class Cordinates {


    private String cordinatesx;


    private String cordinatesy;

    public void setCordinatesx(String cordinatesx) {
        this.cordinatesx = cordinatesx;
    }

    public void setCordinatesy(String cordinatesy) {
        this.cordinatesy = cordinatesy;
    }


    public Cordinates(String cordinatesx, String cordinatesy) {
        this.cordinatesx = cordinatesx;
        this.cordinatesy = cordinatesy;
    }

    public String getX() {
        return cordinatesx;
    }

    public String getY() {
        return cordinatesy;
    }

    @Override
    public String toString() {
        return cordinatesx + " " + cordinatesy;
    }
}
