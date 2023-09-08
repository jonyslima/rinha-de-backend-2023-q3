package org.acme.infra.provider;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.model.EPerson;
import org.acme.domain.model.EStack;
import org.acme.infra.model.Person;
import org.acme.infra.provider.mappers.PersonMapper;
import org.acme.infra.repository.PersonRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class PersonDataProvider implements PersonGateway {
    PersonRepository personRepository;
    StackDataProvider stackDataProvider;
    PersonMapper personMapper;

    @Override
    public Uni<Void> save(EPerson ePerson) {
        Person person = personMapper.toPerson(ePerson);
        person.getStack().forEach(s -> s.setPerson(person));
        return personRepository.persist(person).replaceWithVoid();
    }

    @Override
    public Uni<EPerson> findById(UUID id) {
        return personRepository.findById(id)
                .map(personMapper::toPerson);
    }

    @Override
    public Uni<List<EPerson>> findByTerm(String term) {
        return personRepository.findByTerm(term)
                .map(personMapper::toPersonWithoutStack)
                .onItem()
                .transformToUni(people -> {
                    Map<UUID, EPerson> personById = mapPersonById(people);
                    return stackDataProvider.findStackByIdPerson(personById.keySet())
                            .invoke(stacks -> addStacksToPerson(personById, stacks))
                            .replaceWith(people);
                });
    }

    private static Map<UUID, EPerson> mapPersonById(List<EPerson> people) {
        return people.stream().collect(Collectors.toMap(EPerson::getId, Function.identity()));
    }

    private void addStacksToPerson(Map<UUID, EPerson> personById, Iterable<EStack> stacks) {
        stacks.forEach(stack -> {
            UUID idPerson = stack.getPerson().getId();
            EPerson person = personById.get(idPerson);
            person.addStack(stack);
        });
    }

    @Override
    public Uni<Long> count() {
        return personRepository.count();
    }
}
