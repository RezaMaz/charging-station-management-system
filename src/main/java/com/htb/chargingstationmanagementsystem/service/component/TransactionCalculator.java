package com.htb.chargingstationmanagementsystem.service.component;

import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionCalculator implements ComponentCalculator {
    @Override
    public BigDecimal rate(ChargingRateDto.Request request) {
        return request.getRate().getTransaction();
    }
}
