package com.danielmaia.flights.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.danielmaia.flights.model.Flight;
import com.danielmaia.flights.repository.FlightRepository;
import com.danielmaia.flights.retrofit.responses.FlightResponse;
import com.danielmaia.flights.util.Constants;

import java.util.List;

public class OutboundFragmentViewModel extends AndroidViewModel {

    private FlightRepository flightRepository;
    private LiveData<FlightResponse> flightResponseLiveData;
    private MutableLiveData<Integer> filterCount;
    private LiveData<List<Flight>> listFlightOutboundLiveData;

    public OutboundFragmentViewModel(@NonNull Application application) {
        super(application);

        flightRepository = new FlightRepository();
        filterCount = new MutableLiveData<Integer>();
        this.flightResponseLiveData = flightRepository.getFlightsOnServer();
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

    public LiveData<List<Flight>> getListFlightOutboundOnDatabase(){
        this.listFlightOutboundLiveData = flightRepository.getFlightsOnDatabase(Constants.OUTBOUND);
        return listFlightOutboundLiveData;
    }
}
