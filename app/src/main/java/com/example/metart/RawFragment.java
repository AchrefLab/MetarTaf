package com.example.metart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.metart.models.METAR;
import com.example.metart.models.TAF;

public class RawFragment extends Fragment {
    private METAR metar;
    private TAF taf;
    public RawFragment(METAR metar, TAF taf) {
        this.metar = metar;
        this.taf = taf;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_raw, container, false);
        TextView rawmetar= v.findViewById(R.id.rawmetar); rawmetar.setText(metar.getRaw());
        TextView tafRaw= v.findViewById(R.id.tafRaw); tafRaw.setText(taf.getRaw());
        return  v ;
    }
}