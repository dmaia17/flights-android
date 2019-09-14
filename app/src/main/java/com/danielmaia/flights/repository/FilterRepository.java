package com.danielmaia.flights.repository;

import androidx.lifecycle.MutableLiveData;

import com.danielmaia.flights.AppFlights;
import com.danielmaia.flights.database.FlightDatabase;
import com.danielmaia.flights.model.Filter;
import com.danielmaia.flights.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class FilterRepository {

    private static final String TAG = FlightRepository.class.getSimpleName();

    MutableLiveData<List<Integer>> listIntegersMutableData = new MutableLiveData<>();
    MutableLiveData<Boolean> insertSuccessMutableData = new MutableLiveData<Boolean>();
    MutableLiveData<Boolean> deleteSuccessMutableData = new MutableLiveData<Boolean>();

    public MutableLiveData<List<Integer>> getFilters() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Filter> list = FlightDatabase.getInstance(AppFlights.getInstance()).getFilterDao().getFilters();
                    List<Integer> l = new ArrayList<>();

                    for (Filter filter : list){
                        l.add(filter.getId());
                    }

                    listIntegersMutableData.postValue(l);
                } catch (Exception e) {
                    listIntegersMutableData.postValue(null);
                }
            }
        }).start();

        return listIntegersMutableData;
    }

    public MutableLiveData<Boolean> insertFilters(List<Integer> integerList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FlightDatabase.getInstance(AppFlights.getInstance()).getFilterDao().deleteFilters();

                    for (Integer id : integerList){
                        int type = id <= 3 ? Constants.FILTER_TYPE_TIME : Constants.FILTER_TYPE_STOP;
                        Filter filter = new Filter(id, type);
                        FlightDatabase.getInstance(AppFlights.getInstance()).getFilterDao().insert(filter);
                    }

                    insertSuccessMutableData.postValue(true);
                } catch (Exception e) {
                    insertSuccessMutableData.postValue(false);
                }
            }
        }).start();

        return insertSuccessMutableData;
    }

    public MutableLiveData<Boolean> deleteAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FlightDatabase.getInstance(AppFlights.getInstance()).getFilterDao().deleteFilters();

                    deleteSuccessMutableData.postValue(true);
                } catch (Exception e) {
                    deleteSuccessMutableData.postValue(false);
                }
            }
        }).start();

        return deleteSuccessMutableData;
    }



}
