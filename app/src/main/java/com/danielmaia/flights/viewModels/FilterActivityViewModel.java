package com.danielmaia.flights.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.danielmaia.flights.repository.FilterRepository;

import java.util.List;

public class FilterActivityViewModel extends AndroidViewModel {

    private FilterRepository filterRepository;
    private LiveData<List<Integer>> filterLiveData;
    private LiveData<Boolean> insertSuccessLiveData;
    private LiveData<Boolean> deleteSuccessLiveData;

    public FilterActivityViewModel(@NonNull Application application) {
        super(application);

        filterRepository = new FilterRepository();
        this.filterLiveData = filterRepository.getFilters();

    }

    public LiveData<List<Integer>> getFiltersLiveData() {
        return filterLiveData;
    }

    public LiveData<Boolean> insertFilters(List<Integer> list){
        this.insertSuccessLiveData = filterRepository.insertFilters(list);
        return insertSuccessLiveData;
    }

    public LiveData<Boolean> deleteAllFilters(){
        this.deleteSuccessLiveData = filterRepository.deleteAll();
        return deleteSuccessLiveData;
    }

}
