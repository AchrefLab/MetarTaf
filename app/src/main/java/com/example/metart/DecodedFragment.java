package com.example.metart;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.metart.models.Airport;
import com.example.metart.models.Forecast;
import com.example.metart.models.METAR;
import com.example.metart.models.TAF;
import com.example.metart.services.DataService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class DecodedFragment extends Fragment {
    private String iacoCode;
    private METAR metar;
    private TAF taf;
    private Airport airport;
    private Context mContext;
    public DecodedFragment(METAR metar, TAF taf, Airport airport){
        this.metar = metar;
        this.taf =taf;
        this.airport=airport;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_decoded, container, false);



        TextView flightrule= v.findViewById(R.id.flightrule); flightrule.setText(metar.getflightRules());
        if(metar.getflightRules().equals("IFR")){
            flightrule.setBackgroundResource(R.color.red);
        }if((metar.getflightRules().equals("IFR") == false) &&(metar.getflightRules().equals("VFR")==false)){
            flightrule.setBackgroundResource(R.color.orange);
        }
        TextView tempMetar= v.findViewById(R.id.tempMetar); tempMetar.setText(metar.getTemperature());
        TextView dewpointMetar= v.findViewById(R.id.dewpointMetar); dewpointMetar.setText(metar.getDewpoint());
        TextView pressureMetar= v.findViewById(R.id.pressureMetar); pressureMetar.setText(metar.getAltimeter());
        TextView cloudsMetar= v.findViewById(R.id.cloudsMetar); cloudsMetar.setText(metar.getClouds());
        TextView windMetar= v.findViewById(R.id.windMetar); windMetar.setText(metar.getWind());
        TextView visibilityMetar= v.findViewById(R.id.visibilityMetar); visibilityMetar.setText(metar.getVisibility());

        TextView namestation= v.findViewById(R.id.namestation); namestation.setText(airport.getname());
        TextView citystation= v.findViewById(R.id.citystation); citystation.setText(airport.getCity().concat(", ").concat(airport.getCountry()));
        TextView icaostation= v.findViewById(R.id.icaostation); icaostation.setText(airport.getIcao());
        TextView iatastation= v.findViewById(R.id.iatastation); iatastation.setText(airport.getIata());
        TextView longitude= v.findViewById(R.id.longitude); longitude.setText(airport.getLongitude());

        TextView latitude= v.findViewById(R.id.latitude); latitude.setText(airport.getLatitude());

        Forecast forecast = taf.Forecasts.get(0);
        TextView cloudsTaf= v.findViewById(R.id.cloudsTaf); cloudsTaf.setText(forecast.getClouds());
        TextView visbilityTaf= v.findViewById(R.id.visbilityTaf); visbilityTaf.setText(forecast.getVisibility());
        TextView windTaf= v.findViewById(R.id.windTaf); windTaf.setText(forecast.getWind());

        String nochange = getResources().getString(R.string.nochange);

        Forecast forecast2 = taf.Forecasts.get(1);
        TextView cloudsTaf2= v.findViewById(R.id.cloudsTaf2);
        if(forecast2.getClouds().equals("")){
            cloudsTaf2.setText(nochange);
        }else{
            cloudsTaf2.setText(forecast2.getClouds());
        }

        TextView visbilityTaf2= v.findViewById(R.id.visbilityTaf2);
        if(forecast2.getVisibility().equals("")){
            visbilityTaf2.setText(nochange);
        }else{
            visbilityTaf2.setText(forecast2.getVisibility());
        }

        TextView windTaf2= v.findViewById(R.id.windTaf2);
        if(forecast2.getWind().equals("")){
            windTaf2.setText(nochange);
        }else{
            windTaf2.setText(forecast2.getWind());
        }


        Forecast forecast3 = taf.Forecasts.get(2);
        TextView cloudsTaf3= v.findViewById(R.id.cloudsTaf3);
        if(forecast3.getClouds().equals("")){
            cloudsTaf3.setText(nochange);
        }else{
            cloudsTaf3.setText(forecast3.getClouds());
        }

        TextView visbilityTaf3= v.findViewById(R.id.visbilityTaf3);
        if(forecast3.getVisibility().equals("")){
            visbilityTaf3.setText(nochange);
        }else{
            visbilityTaf3.setText(forecast3.getVisibility());
        }

        TextView windTaf3= v.findViewById(R.id.windTaf3);
        if(forecast3.getWind().equals("")){
            windTaf3.setText(nochange);
        }else{
            windTaf3.setText(forecast3.getWind());
        }

        return  v ;
    }


}