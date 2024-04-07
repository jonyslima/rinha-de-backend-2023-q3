package org.acme.app.rest.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.cross.exceptions.AlreadyExistsNicknameException;

@Provider
@SuppressWarnings("unused")
public class AlreadyExistsNicknameExceptionHandler implements ExceptionMapper<AlreadyExistsNicknameException> {
    private static final int UNPROCESSABLE_ENTITY = 422;

    @Override
    public Response toResponse(AlreadyExistsNicknameException AlreadyExistsNicknameException) {
        return Response.status(UNPROCESSABLE_ENTITY).build();
    }
}
