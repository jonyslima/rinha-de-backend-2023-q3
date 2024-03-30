package org.acme.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.usecase.CountPersonUseCase;

@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class CountPersonUseCaseImpl implements CountPersonUseCase {
    PersonGateway personGateway;

    @Override
    public Uni<Long> execute() {
        return personGateway.count();
    }
}
