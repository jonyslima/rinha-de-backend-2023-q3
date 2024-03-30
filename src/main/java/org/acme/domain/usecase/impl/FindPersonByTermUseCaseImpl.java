package org.acme.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.gateway.StackGateway;
import org.acme.domain.model.EPerson;
import org.acme.domain.model.EStack;
import org.acme.domain.usecase.FindPersonByTermUseCase;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class FindPersonByTermUseCaseImpl implements FindPersonByTermUseCase {
    PersonGateway personGateway;
    StackGateway stackGateway;

    @Override
    public Uni<List<EPerson>> execute(String term) {
        return personGateway.findByTerm(term)
                .onItem()
                .transformToUni(people -> {
                    Map<UUID, EPerson> personById = mapPersonById(people);
                    return stackGateway.findStackByIdPerson(personById.keySet())
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

}
