package org.acme.domain.gateway;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.EPerson;

import java.util.List;
import java.util.UUID;

public interface PersonGateway {

    Uni<EPerson> save(EPerson person);

    Uni<EPerson> findById(UUID id);

    Uni<Boolean> existsByNickname(String nickname);

    Uni<List<EPerson>> findByTerm(String term);

    Uni<Long> count();
}
