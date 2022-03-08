package com.htb.chargingstationmanagementsystem.service;

import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;

public interface Calculator {
    ChargingRateDto.Response rateChargingProcess(ChargingRateDto.Request request);
}
