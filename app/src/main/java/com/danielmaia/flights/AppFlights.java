package com.danielmaia.flights;

import android.app.Application;

public class AppFlights extends Application {

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
}
