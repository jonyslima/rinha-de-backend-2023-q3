package org.acme.domain.gateway;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.EPerson;

import java.util.List;
import java.util.UUID;

public interface PersonGateway {

    public Uni<EPerson> save(EPerson person);

    public Uni<EPerson> findById(UUID id);

    public Uni<Boolean> existsByNickname(String nickname);

    public Uni<List<EPerson>> findByTerm(String term);

    public Uni<Long> count();
}
