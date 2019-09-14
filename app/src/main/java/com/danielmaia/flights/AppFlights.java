package com.danielmaia.flights;

import android.app.Application;

public class AppFlights extends Application {

    private int currentSort;
    private int[] currentFilter;

    private static AppFlights instance;

    public AppFlights() {
        instance = this;
    }

    public synchronized static AppFlights getInstance() {
        if (instance == null)
            new AppFlights();

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int getCurrentSort() {
        return currentSort;
    }

    public void setCurrentSort(int currentSort) {
        this.currentSort = currentSort;
    }

    public int[] getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(int[] currentFilter) {
        this.currentFilter = currentFilter;
    }
}
