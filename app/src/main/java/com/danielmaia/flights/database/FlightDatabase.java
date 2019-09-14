package com.danielmaia.flights.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.danielmaia.flights.dao.FilterDao;
import com.danielmaia.flights.dao.FlightDao;
import com.danielmaia.flights.model.Filter;
import com.danielmaia.flights.model.Flight;

@Database(entities = {Flight.class, Filter.class}, version = 1, exportSchema = false)
public abstract class FlightDatabase extends RoomDatabase {

    private static final String DB_NAME = "voo.db";
    private static volatile FlightDatabase instance;

    public static synchronized FlightDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static FlightDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                FlightDatabase.class,
                DB_NAME).build();
    }

    public abstract FlightDao getFlightDao();
    public abstract FilterDao getFilterDao();

}
