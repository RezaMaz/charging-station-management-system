package com.htb.chargingstationmanagementsystem.service.component;

import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EnergyCalculator implements ComponentCalculator {
    @Override
    public BigDecimal rate(ChargingRateDto.Request request) {
        return request.getRate().getEnergy()
                .multiply(new BigDecimal(request.getCdr().getMeterStop() - request.getCdr().getMeterStart()))
                .setScale(3, RoundingMode.HALF_UP)
                .divide(new BigDecimal(1000), RoundingMode.HALF_UP);
    }
}
