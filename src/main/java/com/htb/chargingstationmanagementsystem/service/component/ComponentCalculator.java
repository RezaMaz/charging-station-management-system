package com.htb.chargingstationmanagementsystem.service.component;

import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;

import java.math.BigDecimal;

public interface ComponentCalculator {
    BigDecimal rate(ChargingRateDto.Request request);
}
