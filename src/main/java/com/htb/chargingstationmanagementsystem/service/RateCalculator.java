package com.htb.chargingstationmanagementsystem.service;

import com.htb.chargingstationmanagementsystem.service.component.EnergyCalculator;
import com.htb.chargingstationmanagementsystem.service.component.TimeCalculator;
import com.htb.chargingstationmanagementsystem.service.component.TransactionCalculator;
import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;
import com.htb.chargingstationmanagementsystem.service.dto.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateCalculator implements Calculator {
    private final EnergyCalculator energyCalculator;
    private final TimeCalculator timeCalculator;
    private final TransactionCalculator transactionCalculator;

    @Override
    public ChargingRateDto.Response rateChargingProcess(ChargingRateDto.Request request) {
        var component = Component.builder()
                .energy(energyCalculator.rate(request))
                .time(timeCalculator.rate(request))
                .transaction(transactionCalculator.rate(request)).build();

        return ChargingRateDto.Response.builder()
                .overall(component.overall())
                .components(component).build();
    }
}
