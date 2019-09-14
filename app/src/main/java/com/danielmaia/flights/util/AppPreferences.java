package com.danielmaia.flights.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.danielmaia.flights.AppFlights;

public class AppPreferences {

    private final String PREFERENCE_CURRENT_SORT = "preference_current_sort";

    private static AppPreferences instance;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private AppPreferences() {
        prefs = PreferenceManager.getDefaultSharedPreferences(AppFlights.getInstance());
        editor = prefs.edit();
    }

    public synchronized static AppPreferences getInstance() {
        if (instance == null)
            instance = new AppPreferences();

        return instance;
    }

    public int getCurrentSort() {
        return prefs.getInt(PREFERENCE_CURRENT_SORT, 0);
    }

    public void setCurrentSort(int sort) {
        editor.putInt(PREFERENCE_CURRENT_SORT, sort);
        editor.commit();
    }



}







