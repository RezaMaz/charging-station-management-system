package com.htb.chargingstationmanagementsystem.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ChargeDetailRecord {
    private Long meterStart;
    private ZonedDateTime timestampStart;
    private Long meterStop;
    private ZonedDateTime timestampStop;
}
