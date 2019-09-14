package com.danielmaia.flights.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.danielmaia.flights.model.Flight;

import java.util.List;


@Dao
public interface FlightDao {


    @Query("SELECT * FROM flight WHERE type = :type")
    List<Flight> getAllFlights(int type);

    @Query("SELECT * FROM flight WHERE type = :type AND period = :p1 ORDER BY saleTotal ASC")
    List<Flight> getAllFlights1Period(int type, int p1);

    @Query("SELECT * FROM flight WHERE type = :type AND period IN (:p1, :p2) ORDER BY saleTotal ASC")
    List<Flight> getAllFlights2Period(int type, int p1, int p2);

    @Query("SELECT * FROM flight WHERE type = :type AND period IN (:p1, :p2, :p3) ORDER BY saleTotal ASC")
    List<Flight> getAllFlights3Period(int type, int p1, int p2, int p3);

    @Query("SELECT * FROM flight WHERE type = :type AND period IN (:p1, :p2, :p3, :p4) ORDER BY saleTotal ASC")
    List<Flight> getAllFlights4Period(int type, int p1, int p2, int p3, int p4);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Flight flight);

}
