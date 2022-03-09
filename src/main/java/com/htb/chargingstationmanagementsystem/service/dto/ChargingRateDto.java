package com.htb.chargingstationmanagementsystem.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ChargingRateDto {

    private ChargingRateDto() {
    }

    @Getter
    @Setter
    @Builder
    public static class Request {
        private Component rate;
        private ChargeDetailRecord cdr;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private BigDecimal overall;
        private Component components;
    }
}