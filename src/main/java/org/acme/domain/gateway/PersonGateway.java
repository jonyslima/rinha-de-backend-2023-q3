package org.acme.domain.gateway;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.EPerson;
import org.acme.infra.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonGateway {

    public Uni<Void> save(EPerson person);

    public Uni<EPerson> findById(UUID id);

    public Uni<List<EPerson>> findByTerm(String term);

    public Uni<Long> count();
}
