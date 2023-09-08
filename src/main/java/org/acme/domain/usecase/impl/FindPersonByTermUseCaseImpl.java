package org.acme.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.model.EPerson;
import org.acme.domain.model.EStack;
import org.acme.domain.usecase.FindPersonByTermUseCase;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class FindPersonByTermUseCaseImpl implements FindPersonByTermUseCase {
    PersonGateway personGateway;

    @Override
    public Uni<List<EPerson>> execute(String term) {
        return personGateway.findByTerm(term);
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
