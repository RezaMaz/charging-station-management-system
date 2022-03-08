package com.htb.chargingstationmanagementsystem.service;

import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;
import org.springframework.stereotype.Service;

@Service
public class RateCalculator implements Calculator {
    @Override
    public ChargingRateDto.Response rateChargingProcess(ChargingRateDto.Request request) {
        return null;
    }
}
