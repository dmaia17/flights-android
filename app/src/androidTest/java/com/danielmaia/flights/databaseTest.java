package com.danielmaia.flights;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.danielmaia.flights.dao.FilterDao;
import com.danielmaia.flights.dao.FlightDao;
import com.danielmaia.flights.database.FlightDatabase;
import com.danielmaia.flights.model.Filter;
import com.danielmaia.flights.model.Flight;
import com.danielmaia.flights.util.Constants;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class databaseTest {

    private FlightDatabase db;

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), FlightDatabase.class).build();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAndDeleteFlightTest() {
        Flight flight = new Flight();
        flight.setStop(10);
        flight.setAirline("azul");
        flight.setDuration(100);
        flight.setFlightNumber("1");
        flight.setFrom("CNF");
        flight.setTo("GRU");
        flight.setDepartureDate(1);
        flight.setArrivalDate(1);
        flight.setSaleTotal(100);
        flight.setType(Constants.OUTBOUND);
        flight.setPeriod(1);

        FlightDao flightDao = db.getFlightDao();
        flightDao.insert(flight);

        List<Flight> flightList = flightDao.getFlightsByStop(10);
        Assert.assertEquals(flightList.get(0).getFlightNumber(), flight.getFlightNumber());

        flightDao.deleteFlightByFlightNumber(flight.getFlightNumber());
        flightList = flightDao.getFlightsByStop(10);
        Assert.assertEquals(flightList.size(), 0);
    }

    @Test
    public void insertAndDeleteFilterTest() {
        Filter filter = new Filter(10, 10);

        FilterDao filterDao = db.getFilterDao();
        filterDao.insert(filter);

        List<Filter> filterList = filterDao.getFiltersByType(10);
        Assert.assertEquals(filterList.get(0).getId(), filter.getId());

        filterDao.deleteFilterById(filter.getId());
        filterList = filterDao.getFiltersByType(10);
        Assert.assertNotEquals(filterList.size(), 0);
    }

}
