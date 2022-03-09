package com.htb.chargingstationmanagementsystem.service.dto;

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
    public static class Request {
        private Component rate;
        private ChargeDetailRecord cdr;
    }

    @Getter
    @Setter
    public static class Response {
        private BigDecimal overall;
        private Component components;
    }
}