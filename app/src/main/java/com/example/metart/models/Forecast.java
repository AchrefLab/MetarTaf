package com.example.metart.models;


public class Forecast {


    private String Altimeter;
    private String Clouds;
    private String Visibility;
    private String Turbulence;
    private String Icing;
    private String Wind;


    public Forecast() {

    }

    public String getAltimeter() {
        return Altimeter;
    }

    public void setAltimeter(String altimeter) {
        Altimeter = altimeter;
    }

    public String getClouds() {
        return Clouds;
    }

    public void setClouds(String clouds) {
        Clouds = clouds;
    }

    public String getVisibility() {
        return Visibility;
    }

    public void setVisibility(String visibility) {
        Visibility = visibility;
    }


    public String getWind() {
        return Wind;
    }

    public void setWind(String wind) {
        Wind = wind;
    }


    public String getTurbulence() {
        return Turbulence;
    }

    public void setTurbulence(String turbulence) {
        Turbulence = turbulence;
    }


    public String getIcing() {
        return Icing;
    }

    public void setIcing(String icing) {
        Icing = icing;
    }


}
