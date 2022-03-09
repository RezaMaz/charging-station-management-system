package com.htb.chargingstationmanagementsystem;

import com.htb.chargingstationmanagementsystem.service.dto.ChargeDetailRecord;
import com.htb.chargingstationmanagementsystem.service.dto.ChargingRateDto;
import com.htb.chargingstationmanagementsystem.service.dto.Component;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ChargingRateControllerIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testProcessRate() {

        // Arrange
        Component rate = new Component();
        rate.setEnergy(new BigDecimal("0.3"));
        rate.setTime(new BigDecimal(2));
        rate.setTransaction(new BigDecimal(1));

        ChargeDetailRecord cdr = new ChargeDetailRecord();
        cdr.setMeterStart(1204307L);
        cdr.setTimestampStart(ZonedDateTime.parse("2021-04-05T10:04:00Z"));
        cdr.setMeterStop(1215230L);
        cdr.setTimestampStop(ZonedDateTime.parse("2021-04-05T11:27:00Z"));

        ChargingRateDto.Request request = new ChargingRateDto.Request();
        request.setRate(rate);
        request.setCdr(cdr);

        // Act
        ResponseEntity<ChargingRateDto.Response> responseEntity =
                this.restTemplate.postForEntity("http://localhost:" + port + "/rate", request,
                        ChargingRateDto.Response.class);

        // Assert
        assertEquals(new BigDecimal("7.04"), responseEntity.getBody().getOverall());
        assertEquals(new BigDecimal("3.277"), responseEntity.getBody().getComponents().getEnergy());
        assertEquals(new BigDecimal("2.767"), responseEntity.getBody().getComponents().getTime());
        assertEquals(new BigDecimal("1"), responseEntity.getBody().getComponents().getTransaction());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
