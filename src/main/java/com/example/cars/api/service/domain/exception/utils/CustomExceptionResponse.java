package com.example.cars.api.service.domain.exception.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class CustomExceptionResponse {
    private String message;
    private String timestamp;

    public CustomExceptionResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
