package org.acme.app.rest.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.exception.ConstraintViolationException;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    private static final int UNPROCESSABLE_ENTITY = 422;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response.status(UNPROCESSABLE_ENTITY).build();
    }
}
