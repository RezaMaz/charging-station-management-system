package com.htb.chargingstationmanagementsystem.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
        @Valid
        @NotNull(message = "rate is required")
        private Component rate;
        @Valid
        @NotNull(message = "cdr is required")
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