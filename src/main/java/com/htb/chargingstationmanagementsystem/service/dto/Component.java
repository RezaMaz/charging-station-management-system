package com.htb.chargingstationmanagementsystem.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@Builder
public class Component {
    private BigDecimal energy;
    private BigDecimal time;
    private BigDecimal transaction;

    public BigDecimal overall() {
        return this.energy.add(this.time).add(this.transaction).setScale(2, RoundingMode.HALF_DOWN);
    }
}
