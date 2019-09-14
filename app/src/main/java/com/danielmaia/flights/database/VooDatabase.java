package com.danielmaia.flights.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.danielmaia.flights.dao.FlightDao;
import com.danielmaia.flights.model.Flight;

@Database(entities = {Flight.class }, version = 1, exportSchema = false)
public abstract class VooDatabase extends RoomDatabase {

    private static final String DB_NAME = "voo.db";
    private static volatile VooDatabase instance;

    public static synchronized VooDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static VooDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                VooDatabase.class,
                DB_NAME).build();
    }

    public abstract FlightDao getFlightDao();

}
