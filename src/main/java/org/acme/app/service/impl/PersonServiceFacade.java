package org.acme.app.service.impl;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.acme.app.rest.dto.reponse.PersonResponse;
import org.acme.app.rest.dto.request.PersonRequest;
import org.acme.app.service.PersonService;
import org.acme.cross.mappers.PersonMapper;
import org.acme.domain.usecase.CountPersonUseCase;
import org.acme.domain.usecase.FindPersonByIdUseCase;
import org.acme.domain.usecase.FindPersonByTermUseCase;
import org.acme.domain.usecase.SavePersonUseCase;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class PersonServiceFacade implements PersonService {
    SavePersonUseCase savePersonUseCase;
    FindPersonByIdUseCase findByIdUseCase;
    PersonMapper personMapper;
    FindPersonByTermUseCase findPersonByTermUseCase;
    CountPersonUseCase countPersonUseCase;

    @Override
    @WithTransaction
    public Uni<Void> save(PersonRequest personRequest) {
        return savePersonUseCase.execute(personMapper.toPerson(personRequest));
    }

    @Override
    @WithSession
    public Uni<PersonResponse> findById(String id) {
        return findByIdUseCase.execute(id)
                .map(personMapper::toPerson);
    }

    @Override
    @WithSession
    public Uni<List<PersonResponse>> findByTerm(String term) {
        return findPersonByTermUseCase.execute(term)
                .map(personMapper::toPerson);
    }

    @Override
    @WithSession
    public Uni<Long> count() {
        return countPersonUseCase.execute();
    }
}
