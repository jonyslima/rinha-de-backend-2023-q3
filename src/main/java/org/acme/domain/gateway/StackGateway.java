package org.acme.domain.gateway;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.EStack;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface StackGateway {
    Uni<List<EStack>> findStackByIdPerson(Set<UUID> ids);
}
