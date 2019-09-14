package com.danielmaia.flights.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.danielmaia.flights.repository.FlightRepository;
import com.danielmaia.flights.retrofit.responses.FlightResponse;

public class PageInboundViewModel extends AndroidViewModel {

    private FlightRepository flightRepository;
    private LiveData<FlightResponse> flightResponseLiveData;
    private MutableLiveData<Integer> filterCount;

    public PageInboundViewModel(@NonNull Application application) {
        super(application);

        filterCount = new MutableLiveData<Integer>();

        flightRepository = new FlightRepository();
        this.flightResponseLiveData = flightRepository.getFlights();
    }

    public LiveData<FlightResponse> getFlightResponseLiveData() {
        return flightResponseLiveData;
    }

    public MutableLiveData<Integer> getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(MutableLiveData<Integer> filterCount) {
        this.filterCount = filterCount;
    }
}
