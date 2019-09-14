package com.danielmaia.flights.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.danielmaia.flights.AppFlights;

public class AppPreferences {

    private final String PREFERENCE_CURRENT_SORT = "preference_current_sort";
    private final String PREFERENCE_CURRENT_FILTER = "preference_current_filter";
    private final String PREFERENCE_CURRENT_FILTER_STOP = "preference_current_filter_stop";

    private static final String LEN_PREFIX = "Count_";
    private static final String VAL_PREFIX = "IntValue_";

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

    public void setCurrentFilter(int[] array){
        editor.putInt(LEN_PREFIX + PREFERENCE_CURRENT_FILTER, array.length);
        int count = 0;

        for (int i: array)
            editor.putInt(VAL_PREFIX + PREFERENCE_CURRENT_FILTER + count++, i);

        editor.commit();
    }

    public int[] getCurrentFilter(){
        int[] ret;
        int count = prefs.getInt(LEN_PREFIX + PREFERENCE_CURRENT_FILTER, 0);

        ret = new int[count];

        for (int i = 0; i < count; i++)
            ret[i] = prefs.getInt(VAL_PREFIX+ PREFERENCE_CURRENT_FILTER + i, i);

        return ret;
    }

    public int getCurrentSort() {
        return prefs.getInt(PREFERENCE_CURRENT_SORT, 0);
    }

    public void setCurrentSort(int sort) {
        editor.putInt(PREFERENCE_CURRENT_SORT, sort);
        editor.commit();
    }

    public int getCurrentStopFilter() {
        return prefs.getInt(PREFERENCE_CURRENT_FILTER_STOP, 0);
    }

    public void setCurrentStopFilter(int stop) {
        editor.putInt(PREFERENCE_CURRENT_FILTER_STOP, stop);
        editor.commit();
    }



}







