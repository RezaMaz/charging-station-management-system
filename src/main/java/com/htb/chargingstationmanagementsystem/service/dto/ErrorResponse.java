package com.htb.chargingstationmanagementsystem.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private HttpStatus status;
    private Long timestamp;
    private String message;
}