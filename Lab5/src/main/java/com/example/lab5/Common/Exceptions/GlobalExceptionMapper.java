package com.example.lab5.Common.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        ErrorResponse errorResponse;
        Response.Status status;

        switch (exception) {
            case IllegalArgumentException ex -> {
                status = Response.Status.BAD_REQUEST;
                errorResponse = new ErrorResponse(status.name(), ex.getMessage());
            }
            case EntityNotFoundException ex -> {
                status = Response.Status.NOT_FOUND;
                errorResponse = new ErrorResponse(status.name(), ex.getMessage());
            }
            default -> {
                status = Response.Status.INTERNAL_SERVER_ERROR;
                errorResponse = new ErrorResponse(status.name(), "An unexpected error occurred");
            }
        }

        return Response.status(status)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorResponse {
        private String code;
        private String message;
    }
}