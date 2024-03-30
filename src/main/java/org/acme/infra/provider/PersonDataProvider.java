package org.acme.infra.provider;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.model.EPerson;
import org.acme.infra.model.Person;
import org.acme.infra.provider.mappers.PersonMapper;
import org.acme.infra.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class PersonDataProvider implements PersonGateway {
    PersonRepository personRepository;
    PersonMapper personMapper;

    @Override
    public Uni<Void> save(EPerson ePerson) {
        Person person = personMapper.toPerson(ePerson);
        person.getStack().forEach(s -> s.setPerson(person));
        return personRepository.persist(person).replaceWithVoid();
    }

    @Override
    public Uni<EPerson> findById(UUID id) {
        return personRepository.findByIdWithStack(id)
                .map(personMapper::toPerson);
    }

    @Override
    public Uni<List<EPerson>> findByTerm(String term) {
        return personRepository.findByTerm(term)
                .map(personMapper::toPersonWithoutStack);
    }

    @Override
    public Uni<Long> count() {
        return personRepository.count();
    }
}
