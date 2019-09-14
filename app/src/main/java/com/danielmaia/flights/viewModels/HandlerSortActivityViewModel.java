package com.danielmaia.flights.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HandlerSortActivityViewModel extends ViewModel {
    private final MutableLiveData<Boolean> optionLowestClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> optionBiggestClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> optionPriceTimeClicked = new MutableLiveData<Boolean>();
    private final MutableLiveData<Boolean> sortButtonClicked = new MutableLiveData<Boolean>();

    public void onOptionLowestClicked(){
        optionLowestClicked.setValue(true);
    }

    public void onBiggestLowestClicked(){
        optionBiggestClicked.setValue(true);
    }

    public void onPriceTimeLowestClicked(){
        optionPriceTimeClicked.setValue(true);
    }

    public void onSortButtonClicked(){
        sortButtonClicked.setValue(true);
    }

    public MutableLiveData<Boolean> getOptionLowestClicked() {
        return optionLowestClicked;
    }

    public MutableLiveData<Boolean> getOptionBiggestClicked() {
        return optionBiggestClicked;
    }

    public MutableLiveData<Boolean> getOptionPriceTimeClicked() {
        return optionPriceTimeClicked;
    }

    public MutableLiveData<Boolean> getSortButtonClicked() {
        return sortButtonClicked;
    }
}
