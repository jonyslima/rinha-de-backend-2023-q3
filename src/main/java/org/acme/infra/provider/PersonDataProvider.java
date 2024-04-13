package org.acme.infra.provider;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.model.EPerson;
import org.acme.infra.cache.NicknameCache;
import org.acme.infra.model.Person;
import org.acme.infra.provider.mappers.PersonMapper;
import org.acme.infra.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@AllArgsConstructor
public class PersonDataProvider implements PersonGateway {
    PersonRepository personRepository;
    NicknameCache nicknameCache;
    PersonMapper personMapper;

    @Override
    public Uni<EPerson> save(EPerson ePerson) {
        Person person = personMapper.toPerson(ePerson);

        return personRepository.persist(person)
                .map(personMapper::toPerson)
                .onItem()
                .transformToUni(p ->
                        nicknameCache.set(p.getNickname()).replaceWith(p)
                );

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
        return nicknameCache.exists(nickname);
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
