package com.example.metart;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.metart.models.Airport;
import com.example.metart.models.METAR;
import com.example.metart.models.TAF;
import com.example.metart.services.DataService;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.metart.databinding.ActivitySearchBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.HashMap;

public class searchActivity extends AppCompatActivity {
    TabLayout tablayout;
    FragmentAdapter adapter;
    ViewPager pager;

    /**********FAKE DATA BASE**********/
    public static HashMap<String, METAR> mappedMETARData = new HashMap<String, METAR>();
    public static HashMap<String, Airport> mappedAirportData = new HashMap<String, Airport>();
    public static HashMap<String, TAF> mappedTAFData = new HashMap<String, TAF>();

    private AppBarConfiguration appBarConfiguration;
    private ActivitySearchBinding binding;
    ImageButton goBack;
    TextInputEditText inputtext;
    ImageButton searchIcao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_search);
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutSearch, new SearchFragment()).commit();
        goBack = (ImageButton) findViewById(R.id.backicon);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchActivity.this, MainActivity.class);
                searchActivity.this.startActivity(intent);
            }
        });


        inputtext = (TextInputEditText) findViewById(R.id.inputtext);
        searchIcao = (ImageButton) findViewById(R.id.searchicon);
        searchIcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String iacoCode =inputtext.getText().toString();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutSearch, new Empty()).commit();


                /*********************************/
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DataService dataser = new DataService();
                        JSONObject metarData = dataser.executeMetarGetRequest(iacoCode);
                        JSONObject airportData = dataser.executeAirportGetRequest(iacoCode);
                        JSONObject tafData = dataser.executeTAFGetRequest(iacoCode);

                        METAR metar = new METAR();
                        Airport airport = new Airport();
                        TAF taf = new TAF();

                        metar=dataser.assignMetarData(metarData,metar);
                        airport=dataser.assignAirportData(airportData,airport);
                        taf = dataser.assignTafData(tafData,taf);
                        mappedMETARData.put(iacoCode,metar);
                        mappedAirportData.put(iacoCode,airport);
                        mappedTAFData.put(iacoCode,taf);

                    }
                });
                th.start();
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                METAR metar = mappedMETARData.get(iacoCode);
                Airport airport = mappedAirportData.get(iacoCode);
                TAF taf = mappedTAFData.get(iacoCode);


                /*********************************/
                tablayout = findViewById(R.id.tab_layout);
                pager = findViewById(R.id.layoutPager);

                String decoded = getResources().getString(R.string.decoded);
                String raw = getResources().getString(R.string.raw);
                tablayout.addTab(tablayout.newTab().setText(decoded));
                tablayout.addTab(tablayout.newTab().setText(raw));
                tablayout.addTab(tablayout.newTab().setText("Map"));


                tablayout.setupWithViewPager(pager);

                FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
                adapter.addFragment(new DecodedFragment(metar,taf,airport),decoded);
                adapter.addFragment(new RawFragment(metar,taf),raw);
                adapter.addFragment(new MapsFragment(airport),"Map");
                pager.setAdapter(adapter);



            }
        });




    }
}