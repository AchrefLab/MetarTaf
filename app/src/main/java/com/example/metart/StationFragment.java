package com.example.metart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.metart.models.METAR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metart.models.Airport;
import com.example.metart.models.METAR;
import com.example.metart.services.DataService;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class StationFragment extends Fragment {
    TextView station;
    RecyclerView recyclerView;
    AirportCardAdapter mAdapter;

    //**** FAKE DATA BASE *****
    public static HashMap<String, METAR> mappedMETARData = new HashMap<String, METAR>();
    public static List<String> mesStations = new ArrayList<String>();
    public static List<METAR> metarList  = new ArrayList<METAR>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mappedMETARData = new HashMap<String, METAR>();
        mesStations = new ArrayList<String>();
        metarList  = new ArrayList<METAR>();
        View v = inflater.inflate(R.layout.fragment_station, container, false);
        mesStations.add("KBUF");
        mesStations.add("LFPG");

        Iterator<String> myIterator = mesStations.iterator();
        while(myIterator.hasNext()){
            requestAndLoadAllAirportData(myIterator.next());
        }

        station = (TextView)v.findViewById(R.id.icao);
        recyclerView = v.findViewById(R.id.airportRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new AirportCardAdapter(getContext(), metarList);
        recyclerView.setAdapter(mAdapter);

        return  v ;
    }

    public static void requestAndLoadAllAirportData(final String airportIdentifier)
    {
        Thread th =new Thread(new Runnable() {
            @Override
            public void run() {
                DataService dataser = new DataService();

                JSONObject metarData = dataser.executeMetarGetRequest(airportIdentifier);
                JSONObject airportData = dataser.executeAirportGetRequest(airportIdentifier);


                METAR metar = new METAR();
                Airport airport = new Airport();

                metar=dataser.assignMetarData(metarData,metar);
                airport=dataser.assignAirportData(airportData,airport);

                metar.setName(airport.getname());
                mappedMETARData.put(airportIdentifier,metar);
                metarList.add(metar);
            }
        });
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}