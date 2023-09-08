package org.acme.infra.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infra.model.Stack;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class StackRepository implements PanacheRepositoryBase<Stack, UUID> {

    public Uni<List<Stack>> findStackByIdPerson(Set<UUID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Uni.createFrom().item(List::of);
        }

        return find("#Stack.findByIdPerson", Map.of("ids", ids)).list();
    }
}
