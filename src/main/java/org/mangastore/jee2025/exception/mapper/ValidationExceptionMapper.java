package org.mangastore.jee2025.exception.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.mangastore.jee2025.dto.ErrorResponse;
import org.mangastore.jee2025.exception.ValidationException;

@Provider
public class ValidationExceptionMapper {

    public static Response toResponse(ValidationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(exception.getMessage()))
                .build();
    }
}