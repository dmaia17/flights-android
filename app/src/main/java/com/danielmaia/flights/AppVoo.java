package com.danielmaia.flights;

import android.app.Application;

public class AppVoo extends Application {

    private int currentSort;
    private int[] currentFilter;

    private static AppVoo instance;

    public AppVoo() {
        instance = this;
    }

    public synchronized static AppVoo getInstance() {
        if (instance == null)
            new AppVoo();

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
