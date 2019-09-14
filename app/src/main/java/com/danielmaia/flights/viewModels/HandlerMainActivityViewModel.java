package com.danielmaia.flights.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;

public class HandlerMainActivityViewModel extends ViewModel {
    private final String TAG = "HandlerMainAct";

    private final MutableLiveData<Boolean> tab1Clicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> tab2Clicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> filterClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> sortClicked = new MutableLiveData<Boolean>();


    public void onTab1Clicked(){
        Log.d(TAG, "Tab 1 clicked");
        tab1Clicked.setValue(true);
    }

    public void onTab2Clicked(){
        Log.d(TAG, "Tab 2 clicked");
        tab2Clicked.setValue(true);
    }

    public void onFilterClicked(){
        Log.d(TAG, "Filter clicked");
        filterClicked.setValue(true);
    }

    public void onSortClicked(){
        Log.d(TAG, "Sort clicked");
        sortClicked.setValue(true);
    }

    public MutableLiveData<Boolean> getTab1Clicked() {
        return tab1Clicked;
    }

    public MutableLiveData<Boolean> getTab2Clicked() {
        return tab2Clicked;
    }

    public MutableLiveData<Boolean> getFilterClicked() {
        return filterClicked;
    }

    public MutableLiveData<Boolean> getSortClicked() {
        return sortClicked;
    }
}
