package com.danielmaia.flights.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.danielmaia.flights.AppFlights;
import com.danielmaia.flights.database.VooDatabase;
import com.danielmaia.flights.model.Flight;
import com.danielmaia.flights.retrofit.ApiRequest;
import com.danielmaia.flights.retrofit.RetrofitRequest;
import com.danielmaia.flights.retrofit.responses.FlightDto;
import com.danielmaia.flights.retrofit.responses.FlightResponse;
import com.danielmaia.flights.util.Constantes;
import com.danielmaia.flights.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightRepository {
    private static final String TAG = FlightRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public FlightRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<FlightResponse> getFlights() {
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
                                            flight.setType(Constantes.OUTBOUND);
                                            flight.setPeriod(Util.getPeriodOfTheDay(flightDto.getDepartureDate()));

                                            VooDatabase.getInstance(AppFlights.getInstance()).getFlightDao().insert(flight);
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
                                            flight.setType(Constantes.INBOUND);
                                            flight.setPeriod(Util.getPeriodOfTheDay(flightDto.getDepartureDate()));

                                            VooDatabase.getInstance(AppFlights.getInstance()).getFlightDao().insert(flight);
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



}
