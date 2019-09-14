package com.danielmaia.flights.retrofit.responses;

import java.util.List;

public class FlightResponse {
    List<FlightDto> outbound;
    List<FlightDto> inbound;

    public FlightResponse() {
    }

    public List<FlightDto> getOutbound() {
        return outbound;
    }

    public void setOutbound(List<FlightDto> outbound) {
        this.outbound = outbound;
    }

    public List<FlightDto> getInbound() {
        return inbound;
    }

    public void setInbound(List<FlightDto> inbound) {
        this.inbound = inbound;
    }
}
