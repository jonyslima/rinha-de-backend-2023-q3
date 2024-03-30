package org.acme.infra.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Value;
import org.acme.infra.model.Person;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Value
@ApplicationScoped
public class PersonRepository implements PanacheRepositoryBase<Person, UUID> {
    private static final Page FIRST_PAGE = new Page(5);

    public Uni<List<Person>> findByTerm(String term) {
        return find("#Person.findByTerm", Map.of("term", String.format("%%%s%%", term))).page(FIRST_PAGE).list();
    }

    public Uni<Person> findByIdWithStack(UUID uuid) {
        return find("#Person.findByIdWithStack", Map.of("id", uuid)).singleResult();
    }
}
