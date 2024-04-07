package org.acme.infra.provider;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
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
    private static final String TAB = "\t";
    PersonRepository personRepository;
    PersonMapper personMapper;

    @Override
    public Uni<EPerson> save(EPerson ePerson) {
        Person person = personMapper.toPerson(ePerson);
        person.setId(UUID.randomUUID());

        if (person.getStack() == null) {
            person.setStack(List.of());
        }

        StringBuilder searchable = new StringBuilder();

        searchable.append(person.getName());
        searchable.append(TAB);
        searchable.append(person.getNickname());
        searchable.append(TAB);

        person.getStack().forEach(s -> {
            s.setId(UUID.randomUUID());
            s.setPerson(person);
            searchable.append(s.getName());
            searchable.append(TAB);
        });

        person.setSearchable(searchable.toString());

        return personRepository.persist(person).map(personMapper::toPerson);
    }

    @Override
    public Uni<EPerson> findById(UUID id) {
        return personRepository.findByIdWithStack(id)
                .map(personMapper::toPerson)
                .onFailure(NoResultException.class)
                .recoverWithNull();
    }

    @Override
    public Uni<Boolean> existsByNickname(String nickname) {
        return personRepository.existsByNickName(nickname);
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
