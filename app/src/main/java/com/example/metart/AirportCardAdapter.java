package com.example.metart;
import com.example.metart.models.METAR;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class AirportCardAdapter extends RecyclerView.Adapter<AirportCardAdapter.ViewHolder> {
    private List<METAR> listMetar;
    private Context mContext;

    public AirportCardAdapter(Context context, List<METAR> listMetar) {
        this.listMetar = listMetar;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View artistView = LayoutInflater.from(parent.getContext()).inflate(R.layout.airport_card, parent, false);
        ViewHolder vh = new ViewHolder(artistView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AirportCardAdapter.ViewHolder holder, int position) {
        final METAR metar = listMetar.get(position);
        if(metar.getName().length()>34){
            holder.airport.setText(metar.getName().substring(0,34));
        }else{
            holder.airport.setText(metar.getName());
        }
        holder.flightrules.setText(metar.getflightRules());
        if(metar.getflightRules().equals("IFR")){
            holder.flightrules.setBackgroundResource(R.color.red);
        }if((metar.getflightRules().equals("IFR") == false) &&(metar.getflightRules().equals("VFR")==false)){
            holder.flightrules.setBackgroundResource(R.color.orange);
        }
        holder.icao.setText(metar.getStation());
        holder.windDirection.setText(metar.getWinddir());
        holder.windspeed.setText(metar.getWindspeed());
        holder.temperature.setText(metar.getTemperature());
        holder.pressure.setText(metar.getAltimeter());
        holder.visbility.setText(metar.getVisibility());

        holder.airport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(mContext, AlbumActivity.class);
                //intent.putExtra("nom", metar.getName());
                //mContext.startActivity(intent);

            }
        });
    }

    public void setListArtist(List<METAR> listMetar) {
        this.listMetar = listMetar;
    }

    @Override
    public int getItemCount() {
        return listMetar.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView airport;
        private final TextView flightrules;
        private final TextView icao;
        private final TextView windDirection;
        private final TextView windspeed;
        private final TextView temperature;
        private final TextView pressure;
        private final TextView visbility;

        public ViewHolder(View view) {
            super(view);
            airport = view.findViewById(R.id.airport);
            flightrules = view.findViewById(R.id.flightrules);
            icao = view.findViewById(R.id.icao);
            windDirection = view.findViewById(R.id.windDirection);
            windspeed = view.findViewById(R.id.windspeed);
            temperature = view.findViewById(R.id.temperature);
            pressure = view.findViewById(R.id.pressure);
            visbility = view.findViewById(R.id.visbility);

        }

    }
}
