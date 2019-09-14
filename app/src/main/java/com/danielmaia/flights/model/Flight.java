package com.danielmaia.flights.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Flight {

    @PrimaryKey @NonNull
    private String flightNumber;
    private int stop;
    private String airline;
    private long duration;
    private String from;
    private String to;
    private long departureDate;
    private long arrivalDate;
    private double saleTotal;
    private int type;
    private int period;

    public Flight() {
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(long departureDate) {
        this.departureDate = departureDate;
    }

    public long getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public double getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(double saleTotal) {
        this.saleTotal = saleTotal;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
