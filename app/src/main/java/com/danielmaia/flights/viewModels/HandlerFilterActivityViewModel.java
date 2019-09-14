package com.danielmaia.flights.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class HandlerFilterActivityViewModel extends ViewModel {

    private final MutableLiveData<Boolean> timeMorningClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> timeAfternoonClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> timeNightClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> timeDawnClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> stopNoClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> stopOneClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> filterButtonClicked = new MutableLiveData<Boolean>();


    public void onTimeMorningClicked(){
        timeMorningClicked.setValue(true);
    }

    public void onTimeAfternoonClicked(){
        timeAfternoonClicked.setValue(true);
    }

    public void onTimeNightClicked(){
        timeNightClicked.setValue(true);
    }

    public void onTimeDawnClicked(){
        timeDawnClicked.setValue(true);
    }

    public void onStopNoClicked (){
        stopNoClicked.setValue(true);
    }

    public void onStopOneClicked(){
        stopOneClicked.setValue(true);
    }

    public void onFilterButtonClicked(){
        filterButtonClicked.setValue(true);
    }



    public MutableLiveData<Boolean> getTimeMorningClicked() {
        return timeMorningClicked;
    }

    public MutableLiveData<Boolean> getTimeAfternoonClicked() {
        return timeAfternoonClicked;
    }

    public MutableLiveData<Boolean> getTimeNightClicked() {
        return timeNightClicked;
    }

    public MutableLiveData<Boolean> getTimeDawnClicked() {
        return timeDawnClicked;
    }

    public MutableLiveData<Boolean> getStopNoClicked() {
        return stopNoClicked;
    }

    public MutableLiveData<Boolean> getStopOneClicked() {
        return stopOneClicked;
    }

    public MutableLiveData<Boolean> getFilterButtonClicked() {
        return filterButtonClicked;
    }
}
