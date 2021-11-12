package com.example.metart.models;

public class METAR {


    public METAR(){

    }

    public METAR(String altimeter, String clouds, String visibility, String dewpoint, String temperature, String wind, String flightRules) {
        Altimeter = altimeter;
        Clouds = clouds;
        Visibility = visibility;
        Dewpoint = dewpoint;
        Temperature = temperature;
        Wind = wind;
        FlightRules = flightRules;
    }
    private String Name;
    private String Station;
    private String Altimeter;
    private String Clouds;
    private String Visibility;
    private String Dewpoint;
    private String Temperature;
    private String Wind;
    private String Winddir;
    private String Windspeed;
    private String Raw;
    private String FlightRules;

    public String getWinddir() {
        return Windspeed;
    }
    public void setWindir(String windspeed){ Windspeed=windspeed;}

    public String getWindspeed() {
        return Winddir;
    }
    public void setWindspeed(String winddir){ Winddir=winddir;}

    public String getRaw() {
        return Raw;
    }

    public void setRaw(String raw) {
        Raw = raw;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getStation() {
        return Station;
    }

    public void setStation(String station) {
        Station = station;
    }

    public String getflightRules() {
        return FlightRules;
    }

    public void setflightRules(String flightrules) {
        FlightRules = flightrules;
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

    public String getDewpoint() {
        return Dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        Dewpoint = dewpoint;
    }

    public String getVisibility() {
        return Visibility;
    }

    public void setVisibility(String visibility) {
        Visibility = visibility;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getWind() {
        return Wind;
    }

    public void setWind(String wind) {
        Wind = wind;
    }
}
