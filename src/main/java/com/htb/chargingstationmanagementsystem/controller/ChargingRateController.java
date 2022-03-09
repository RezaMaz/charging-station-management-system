package com.htb.chargingstationmanagementsystem.controller;

import com.htb.chargingstationmanagementsystem.service.Calculator;
import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChargingRateController {

    private final Calculator calculator;

    @PostMapping("/rate")
    ResponseEntity<ChargingRateDto.Response> processRate(@RequestBody ChargingRateDto.Request request) {
        return new ResponseEntity<>(calculator.rateChargingProcess(request), HttpStatus.OK);
    }
}
