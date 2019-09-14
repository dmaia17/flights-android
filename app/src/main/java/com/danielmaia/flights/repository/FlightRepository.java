package com.danielmaia.flights.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.danielmaia.flights.AppFlights;
import com.danielmaia.flights.database.FlightDatabase;
import com.danielmaia.flights.model.Filter;
import com.danielmaia.flights.model.Flight;
import com.danielmaia.flights.retrofit.ApiRequest;
import com.danielmaia.flights.retrofit.RetrofitRequest;
import com.danielmaia.flights.retrofit.responses.FlightDto;
import com.danielmaia.flights.retrofit.responses.FlightResponse;
import com.danielmaia.flights.util.Constants;
import com.danielmaia.flights.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightRepository {
    private static final String TAG = FlightRepository.class.getSimpleName();

    private ApiRequest apiRequest;
    MutableLiveData<List<Flight>> listFlightMutableData = new MutableLiveData<>();

    public FlightRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<FlightResponse> getFlightsOnServer() {
        final MutableLiveData<FlightResponse> data = new MutableLiveData<>();

        apiRequest.getFlights()
                .enqueue(new Callback<FlightResponse>() {

                    @Override
                    public void onResponse(Call<FlightResponse> call, Response<FlightResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response.body());

                        if (response.body() != null) {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    if (response.body().getOutbound().size() > 0) {

                                        for (FlightDto flightDto : response.body().getOutbound()){
                                            Flight flight = new Flight();

                                            flight.setStop(flightDto.getStop());
                                            flight.setAirline(flightDto.getAirline());
                                            flight.setDuration(flightDto.getDuration());
                                            flight.setFlightNumber(flightDto.getFlightNumber());
                                            flight.setFrom(flightDto.getFrom());
                                            flight.setTo(flightDto.getTo());
                                            flight.setDepartureDate(flightDto.getDepartureDate().getTime());
                                            flight.setArrivalDate(flightDto.getArrivalDate().getTime());
                                            flight.setSaleTotal(flightDto.getPricing().getOta().getSaleTotal());
                                            flight.setType(Constants.OUTBOUND);
                                            flight.setPeriod(Util.getPeriodOfTheDay(flightDto.getDepartureDate()));

                                            FlightDatabase.getInstance(AppFlights.getInstance()).getFlightDao().insert(flight);
                                        }

                                        for (FlightDto flightDto : response.body().getInbound()){
                                            Flight flight = new Flight();

                                            flight.setStop(flightDto.getStop());
                                            flight.setAirline(flightDto.getAirline());
                                            flight.setDuration(flightDto.getDuration());
                                            flight.setFlightNumber(flightDto.getFlightNumber());
                                            flight.setFrom(flightDto.getFrom());
                                            flight.setTo(flightDto.getTo());
                                            flight.setDepartureDate(flightDto.getDepartureDate().getTime());
                                            flight.setArrivalDate(flightDto.getArrivalDate().getTime());
                                            flight.setSaleTotal(flightDto.getPricing().getOta().getSaleTotal());
                                            flight.setType(Constants.INBOUND);
                                            flight.setPeriod(Util.getPeriodOfTheDay(flightDto.getDepartureDate()));

                                            FlightDatabase.getInstance(AppFlights.getInstance()).getFlightDao().insert(flight);
                                        }
                                    }

                                    data.postValue(response.body());
                                }
                            });


                        }
                    }

                    @Override
                    public void onFailure(Call<FlightResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public MutableLiveData<List<Flight>> getFlightsOnDatabase(int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Flight> list = new ArrayList<>();
                    List<Flight> databaseList =  FlightDatabase.getInstance(AppFlights.getInstance())
                                                    .getFlightDao().getFlightsByType(type);
                    List<Filter> timesFilter = FlightDatabase.getInstance(AppFlights.getInstance())
                                                    .getFilterDao().getFiltersByType(Constants.FILTER_TYPE_TIME);
                    List<Filter> stopsFilter = FlightDatabase.getInstance(AppFlights.getInstance())
                            .getFilterDao().getFiltersByType(Constants.FILTER_TYPE_STOP);

                    for (Flight flight : databaseList){
                        boolean filterOk = true;

                        if (timesFilter.size() > 0) {
                            filterOk = false;
                            for (Filter filterTime : timesFilter) {
                                if (flight.getPeriod() == filterTime.getId()) {
                                    filterOk = true;
                                    break;
                                }
                            }
                        }

                        if (stopsFilter.size() > 0 && filterOk) {
                            filterOk = false;
                            for (Filter filterStop : stopsFilter) {
                                int stopTemp = filterStop.getId() == 4 ? 0 : 1; //TODO: Ajustar o filtro de stops
                                if (flight.getStop() == stopTemp) {
                                    filterOk = true;
                                    break;
                                }
                            }
                        }

                        if (filterOk)
                            list.add(flight);
                    }

                    listFlightMutableData.postValue(list);
                } catch (Exception e) {
                    listFlightMutableData.postValue(null);
                }
            }
        }).start();

        return listFlightMutableData;
    }



}
