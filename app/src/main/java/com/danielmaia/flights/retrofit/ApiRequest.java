package com.danielmaia.flights.retrofit;

import com.danielmaia.flights.retrofit.responses.FlightResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("/hmg-search")
    Call<FlightResponse> getFlights();

}
