package com.danielmaia.flights.retrofit.responses;

import java.util.Date;

public class FlightDto {

    private int stop;
    private String airline;
    private long duration;
    private String flightNumber;
    private String from;
    private String to;
    private Date departureDate;
    private Date arrivalDate;
    private PricingDto pricing;

    public FlightDto() {
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public PricingDto getPricing() {
        return pricing;
    }

    public void setPricing(PricingDto pricing) {
        this.pricing = pricing;
    }
}
