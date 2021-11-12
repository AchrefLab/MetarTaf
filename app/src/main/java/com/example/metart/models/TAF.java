package com.example.metart.models;

import java.util.ArrayList;
import java.util.List;

public class TAF {

    public List<Forecast> Forecasts = new ArrayList<Forecast>();

    public void addForecast(Forecast forecast)
    {
        Forecasts.add(forecast);

    }

    private String Raw;

    public String getRaw() {
        return Raw;
    }

    public void setRaw(String raw) {
        Raw = raw;
    }

}
