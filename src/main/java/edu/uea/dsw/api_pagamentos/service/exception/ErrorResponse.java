package edu.uea.dsw.api_pagamentos.service.exception;

import java.time.Instant;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private Instant timestamp;

    public ErrorResponse(int status, String message, Instant timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
