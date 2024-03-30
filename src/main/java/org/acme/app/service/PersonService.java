package org.acme.app.service;

import io.smallrye.mutiny.Uni;
import org.acme.app.rest.dto.reponse.PersonResponse;
import org.acme.app.rest.dto.request.PersonRequest;

import java.util.List;

public interface PersonService {

    public Uni<PersonResponse> save(PersonRequest personRequest);
    public Uni<PersonResponse> findById(String id);
    public Uni<List<PersonResponse>> findByTerm(String term);
    public  Uni<Long> count();

}
