package org.acme.domain.gateway;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.EStack;
import org.acme.infra.model.Stack;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface StackGateway {
    public Uni<List<EStack>> findStackByIdPerson(Set<UUID> ids);
}
