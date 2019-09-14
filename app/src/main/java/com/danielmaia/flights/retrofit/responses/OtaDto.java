package com.danielmaia.flights.retrofit.responses;

public class OtaDto {
    private double saleTotal;

    public OtaDto(double saleTotal) {
        this.saleTotal = saleTotal;
    }

    public double getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(double saleTotal) {
        this.saleTotal = saleTotal;
    }
}
