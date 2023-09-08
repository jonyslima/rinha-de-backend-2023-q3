package org.acme.app.rest.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.acme.app.rest.dto.request.PersonRequest;
import org.acme.app.service.PersonService;
import org.apache.commons.lang3.StringUtils;

@Value
@Slf4j
@Path("/")
@AllArgsConstructor(onConstructor = @__(@Inject))
public class PersonResource {
    PersonService personService;

    @POST
    @Path("pessoas")
    public Uni<Response> save(@Valid PersonRequest personRequest) {
        return personService.save(personRequest)
                .map(Response::ok)
                .map(Response.ResponseBuilder::build);
    }

    @GET
    @Path("pessoas/{id}")
    public Uni<Response> findById(@PathParam("id") String id) {
        return personService.findById(id)
                .onItem().ifNotNull().transform(p -> Response.ok(p).build())
                .onItem().ifNull().continueWith(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("pessoas")
    public Uni<Response> findByTerm(@QueryParam("t") String term) {
        if (StringUtils.isBlank(term)) {
            return Uni.createFrom()
                    .item(Response.status(Response.Status.BAD_REQUEST).build());
        }

        return personService.findByTerm(term)
                .map(Response::ok)
                .map(Response.ResponseBuilder::build);
    }

    @GET
    @Path("contagem-pessoas")
    public Uni<Response> countPerson() {
        return personService.count()
                .map(Response::ok)
                .map(Response.ResponseBuilder::build);
    }


}

