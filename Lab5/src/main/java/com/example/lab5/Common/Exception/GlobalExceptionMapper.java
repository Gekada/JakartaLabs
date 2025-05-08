package com.example.lab5.common.exception;

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
        ErrorResponse errorResponse = new ErrorResponse();
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        if (exception instanceof IllegalArgumentException) {
            errorResponse.setCode(Response.Status.BAD_REQUEST.name());
            errorResponse.setMessage(exception.getMessage());
            status = Response.Status.BAD_REQUEST;
        } else if (exception instanceof EntityNotFoundException) {
            errorResponse.setCode(Response.Status.NOT_FOUND.name());
            errorResponse.setMessage(exception.getMessage());
            status = Response.Status.NOT_FOUND;
        }

        return Response.status(status)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ErrorResponse {
        private String code;
        private String message;
    }
}