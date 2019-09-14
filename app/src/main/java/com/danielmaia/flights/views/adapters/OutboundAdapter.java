package com.danielmaia.flights.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danielmaia.flights.AppFlights;
import com.danielmaia.flights.R;
import com.danielmaia.flights.model.Flight;
import com.danielmaia.flights.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OutboundAdapter extends RecyclerView.Adapter<OutboundAdapter.ViewHolder> {

    List<Flight> flightList = new ArrayList<>();

    public OutboundAdapter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public OutboundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_flights,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutboundAdapter.ViewHolder viewHolder, int i) {
        final Flight flight = flightList.get(i);

        viewHolder.txtFrom.setText(flight.getFrom().toUpperCase());
        viewHolder.txtTo.setText(flight.getTo().toUpperCase());
        viewHolder.txtDepartureTime.setText(Util.getHourAndMinAsString(new Date(flight.getDepartureDate())));
        viewHolder.txtArrivalTime.setText(Util.getHourAndMinAsString(new Date(flight.getArrivalDate())));

        int stops = flight.getStop();
        String time = Util.getHourHMinAsString(flight.getDuration());

        String durationAndStop = String.format(stops > 1 ? AppFlights.getInstance().getResources().getString(R.string.main_adapter_stops) : AppFlights.getInstance().getResources().getString(R.string.main_adapter_stop), time, stops);

        viewHolder.txtDurationStop.setText(durationAndStop);
        viewHolder.txtPrice.setText(String.format(AppFlights.getInstance().getResources().getString(R.string.main_adapter_price), String.format("%.2f", flight.getSaleTotal()) ));

        viewHolder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFrom, txtTo, txtDepartureTime, txtArrivalTime, txtDurationStop, txtPrice;
        private Button btnBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFrom = itemView.findViewById(R.id.txtFrom);
            txtTo = itemView.findViewById(R.id.txtTo);
            txtDepartureTime = itemView.findViewById(R.id.txtDepartureTime);
            txtArrivalTime = itemView.findViewById(R.id.txtArrivalTime);
            txtDurationStop = itemView.findViewById(R.id.txtDurationStop);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnBuy = itemView.findViewById(R.id.btnBuy);
        }
    }
}

