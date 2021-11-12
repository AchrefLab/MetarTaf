package com.example.metart.services;
import com.example.metart.models.Airport;
import com.example.metart.models.Forecast;
import com.example.metart.models.METAR;
import com.example.metart.models.TAF;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DataService {
    public DataService()
    {

    }
// ******** BASE DATA SERVICE *************
    public static JSONObject executeGet(String targetURL) {

        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);


            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "0");
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {

                    content.append(line);
                    content.append(System.lineSeparator());
                }

            }
            JSONObject myObject = new JSONObject(String.valueOf(content));

            System.out.println("GET response: " + myObject);
            return myObject;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    // ******** GET METAR DATA SERVICE *************
    public static JSONObject executeMetarGetRequest(String airportIdentifier){

        String targetURL = "https://avwx.rest/api/metar/" + airportIdentifier + "?options=translate&airport=true&format=json&onfail=cache&token=cR1YTgW5Kc9zi7pNHtpLmE7o2cVszBFCK6r6OVKJUek";

        return executeGet(targetURL);

    }


    // ******** GET AIRPORT DATA SERVICE *************
    public static JSONObject executeAirportGetRequest(String airportIdentifier){

        String targetURL = "https://avwx.rest/api/station/" + airportIdentifier + "?options=translate&airport=true&format=json&onfail=cache&token=cR1YTgW5Kc9zi7pNHtpLmE7o2cVszBFCK6r6OVKJUek";

        return executeGet(targetURL);

    }

    // ******** GET TAF DATA SERVICE *************
    public static JSONObject executeTAFGetRequest(String airportIdentifier){

        String targetURL = "https://avwx.rest/api/taf/" + airportIdentifier + "?options=translate&airport=true&format=json&onfail=cache&token=cR1YTgW5Kc9zi7pNHtpLmE7o2cVszBFCK6r6OVKJUek";

        return executeGet(targetURL);

    }




//********** ASIGNING DATA **************

    public static METAR assignMetarData(JSONObject metarData, METAR METARObj) {
        try {

            METARObj.setRaw(metarData.getString("raw"));
            JSONObject metarTranslate = metarData.getJSONObject("translate");
            METARObj.setAltimeter(metarData.getJSONObject("altimeter").getString("value").toString().concat(" ").concat(metarData.getJSONObject("units").getString("altimeter")));
            METARObj.setClouds(metarTranslate.getString("clouds"));
            METARObj.setDewpoint(metarTranslate.getString("dewpoint"));
            METARObj.setTemperature(metarTranslate.getString("temperature"));
            METARObj.setVisibility(metarTranslate.getString("visibility"));
            METARObj.setWind(metarTranslate.getString("wind"));
            METARObj.setWindir(metarData.getJSONObject("wind_direction").getString("repr"));
            METARObj.setWindspeed(metarData.getJSONObject("wind_speed").getString("value").toString().concat(" kt"));
            METARObj.setflightRules(metarData.getString("flight_rules"));
            METARObj.setStation(metarData.getString("station"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return METARObj;
    }

    public static Airport assignAirportData(JSONObject airportData, Airport AirportObj) {

        try {
            AirportObj.setName(airportData.getString("name"));
            AirportObj.setCity(airportData.getString("city"));
            AirportObj.setCountry(airportData.getString("country"));
            AirportObj.setIata(airportData.getString("iata"));
            AirportObj.setIcao(airportData.getString("icao"));
            AirportObj.setLatitude(airportData.getString("latitude").toString());
            AirportObj.setLongitude(airportData.getString("longitude").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return AirportObj;
    }



    public static TAF assignTafData(JSONObject tafData, TAF TAFObj) {

        try {
            TAFObj.setRaw(tafData.getString("raw"));
            JSONArray metarTranslate = tafData.getJSONObject("translate").getJSONArray("forecast");
            for(int i=0;i<metarTranslate.length();i++)
            {
                JSONObject JSONForecast = metarTranslate.getJSONObject(i);
                Forecast forecast = new Forecast();

                forecast.setAltimeter(JSONForecast.getString("altimeter"));
                forecast.setClouds(JSONForecast.getString("clouds"));
                forecast.setTurbulence(JSONForecast.getString("turbulence"));
                forecast.setIcing(JSONForecast.getString("icing"));
                forecast.setVisibility(JSONForecast.getString("visibility"));
                forecast.setWind(JSONForecast.getString("wind"));

                TAFObj.addForecast(forecast);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return TAFObj;
    }
}
