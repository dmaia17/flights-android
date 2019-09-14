package com.danielmaia.flights.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.danielmaia.flights.repository.FlightRepository;
import com.danielmaia.flights.retrofit.responses.FlightResponse;

public class MainActivityViewModel extends AndroidViewModel {

    private FlightRepository flightRepository;
    private LiveData<FlightResponse> flightResponseLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        flightRepository = new FlightRepository();
        this.flightResponseLiveData = flightRepository.getFlights();
    }

    public LiveData<FlightResponse> getFlightResponseLiveData() {
        return flightResponseLiveData;
    }

}
