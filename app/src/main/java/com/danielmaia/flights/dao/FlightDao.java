package com.danielmaia.flights.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.danielmaia.flights.model.Flight;

import java.util.List;


@Dao
public interface FlightDao {

    @Query("SELECT * FROM flight")
    List<Flight> getAllFlights();

    @Query("SELECT * FROM flight WHERE type = :type")
    List<Flight> getFlightsByType(int type);

    @Query("SELECT * FROM flight WHERE period = :period")
    List<Flight> getFlightsByPeriod(int period);

    @Query("SELECT * FROM flight WHERE stop = :stop")
    List<Flight> getFlightsByStop(int stop);

    @Query("DELETE FROM flight WHERE flightNumber = :id")
    void deleteFlightByFlightNumber(String id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Flight flight);

}
