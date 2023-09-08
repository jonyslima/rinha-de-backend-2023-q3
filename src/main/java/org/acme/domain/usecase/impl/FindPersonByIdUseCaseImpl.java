package org.acme.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import io.smallrye.mutiny.unchecked.UncheckedFunction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.model.EPerson;
import org.acme.domain.usecase.FindPersonByIdUseCase;

import java.util.UUID;

@Value
@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class FindPersonByIdUseCaseImpl implements FindPersonByIdUseCase {
    PersonGateway personGateway;

    @Override
    public Uni<EPerson> execute(String id) {
        return Uni.createFrom().item(id)
                .map(UUID::fromString)
                .onItem()
                .transformToUni(personGateway::findById)
                .onFailure(IllegalArgumentException.class).recoverWithNull();
    }
}
