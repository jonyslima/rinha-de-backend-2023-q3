package org.acme.app.rest.handler;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {
    private static final int UNPROCESSABLE_ENTITY = 422;

    @Override
    public Response toResponse(ValidationException exception) {
        log.error("teste", exception);
        return Response.status(UNPROCESSABLE_ENTITY).build();
    }
}
