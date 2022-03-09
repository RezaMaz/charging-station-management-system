package com.htb.chargingstationmanagementsystem.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
public class ChargeDetailRecord {
    @NotNull(message = "meterStart is required")
    private Long meterStart;
    @NotNull(message = "timestampStart is required")
    private ZonedDateTime timestampStart;
    @NotNull(message = "meterStop is required")
    private Long meterStop;
    @NotNull(message = "timestampStop is required")
    private ZonedDateTime timestampStop;
}
