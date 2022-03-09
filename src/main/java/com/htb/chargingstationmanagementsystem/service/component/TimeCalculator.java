package com.htb.chargingstationmanagementsystem.service.component;

import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

@Service
public class TimeCalculator implements ComponentCalculator {
    @Override
    public BigDecimal rate(ChargingRateDto.Request request) {
        return request.getRate().getTime() == null ? BigDecimal.ZERO :
                request.getRate().getTime()
                        .multiply(new BigDecimal(Duration.between(request.getCdr().getTimestampStart(),
                                request.getCdr().getTimestampStop()).toSeconds()))
                        .divide(new BigDecimal(3600), 4, RoundingMode.DOWN)
                        .setScale(3, RoundingMode.UP);
    }
}
