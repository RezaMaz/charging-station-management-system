package com.htb.chargingstationmanagementsystem.service;

import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;
import com.htb.chargingstationmanagementsystem.service.dto.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

@Service
public class RateCalculator implements Calculator {
    @Override
    public ChargingRateDto.Response rateChargingProcess(ChargingRateDto.Request request) {
        Component component = new Component();

        component.setEnergy(request.getRate().getEnergy()
                .multiply(new BigDecimal(request.getCdr().getMeterStop() - request.getCdr().getMeterStart()))
                .setScale(3, RoundingMode.HALF_UP)
                .divide(new BigDecimal(1000), RoundingMode.HALF_UP));

        component.setTime(request.getRate().getTime()
                .multiply(new BigDecimal(Duration.between(request.getCdr().getTimestampStart(),
                        request.getCdr().getTimestampStop()).toSeconds()))
                .divide(new BigDecimal(3600), 4, RoundingMode.DOWN)
                .setScale(3, RoundingMode.UP));

        component.setTransaction(request.getRate().getTransaction());

        ChargingRateDto.Response response = new ChargingRateDto.Response();
        response.setOverall(component.getEnergy().add(component.getTime()).add(component.getTransaction())
                .setScale(2, RoundingMode.HALF_DOWN));
        response.setComponents(component);
        return response;
    }
}
