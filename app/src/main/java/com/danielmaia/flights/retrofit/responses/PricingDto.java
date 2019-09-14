package com.danielmaia.flights.retrofit.responses;

public class PricingDto {
    private OtaDto ota;

    public PricingDto() {
    }

    public OtaDto getOta() {
        return ota == null ? new OtaDto(0) : ota;
    }

    public void setOta(OtaDto ota) {
        this.ota = ota;
    }
}
