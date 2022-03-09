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
    void GivenRateAndCdr_WhenProcessRating_ThenShouldReturnOveralAndComponents() {

        // Arrange
        var rate = Component.builder()
                .energy(new BigDecimal("0.3"))
                .time(new BigDecimal(2))
                .transaction(new BigDecimal(1)).build();

        var cdr = ChargeDetailRecord.builder()
                .meterStart(1204307L)
                .timestampStart(ZonedDateTime.parse("2021-04-05T10:04:00Z"))
                .meterStop(1215230L)
                .timestampStop(ZonedDateTime.parse("2021-04-05T11:27:00Z")).build();

        var request = ChargingRateDto.Request.builder()
                .rate(rate)
                .cdr(cdr).build();

        // Act
        var responseEntity =
                this.restTemplate.withBasicAuth("root", "root").postForEntity("http://localhost:" + port + "/v1/rate"
                        , request,
                        ChargingRateDto.Response.class);

        // Assert
        assertEquals(new BigDecimal("7.04"), responseEntity.getBody().getOverall());
        assertEquals(new BigDecimal("3.277"), responseEntity.getBody().getComponents().getEnergy());
        assertEquals(new BigDecimal("2.767"), responseEntity.getBody().getComponents().getTime());
        assertEquals(new BigDecimal("1"), responseEntity.getBody().getComponents().getTransaction());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void MissingCdrFields_WhenProcessRating_ThenShouldReturn400() {

        // Arrange
        var rate = Component.builder()
                .energy(new BigDecimal("0.3"))
                .transaction(new BigDecimal(1)).build();

        var cdr = ChargeDetailRecord.builder()
                .meterStop(1215230L)
                .timestampStop(ZonedDateTime.parse("2021-04-05T11:27:00Z")).build();

        var request = ChargingRateDto.Request.builder()
                .rate(rate)
                .cdr(cdr).build();

        // Act
        var responseEntity =
                this.restTemplate.withBasicAuth("root", "root").postForEntity("http://localhost:" + port + "/v1/rate"
                        , request,
                        ChargingRateDto.Response.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
