package com.danielmaia.flights.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;

public class HandlerMainActivityViewModel extends ViewModel {
    private final String TAG = "HandlerMainAct";

    private final MutableLiveData<Boolean> mTab1Clicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> mTab2Clicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> mFilterClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> mSortClicked = new MutableLiveData<Boolean>();


    public void onTab1Clicked(){
        Log.d(TAG, "Tab 1 clicked");
        mTab1Clicked.setValue(true);
    }

    public void onTab2Clicked(){
        Log.d(TAG, "Tab 2 clicked");
        mTab2Clicked.setValue(true);
    }

    public void onFilterClicked(){
        Log.d(TAG, "Filter clicked");
        mFilterClicked.setValue(true);
    }

    public void onSortClicked(){
        Log.d(TAG, "Sort clicked");
        mSortClicked.setValue(true);
    }

    public MutableLiveData<Boolean> getmTab1Clicked() {
        return mTab1Clicked;
    }

    public MutableLiveData<Boolean> getmTab2Clicked() {
        return mTab2Clicked;
    }

    public MutableLiveData<Boolean> getmFilterClicked() {
        return mFilterClicked;
    }

    public MutableLiveData<Boolean> getmSortClicked() {
        return mSortClicked;
    }
}
