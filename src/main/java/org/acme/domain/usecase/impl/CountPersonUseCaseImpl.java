package org.acme.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.usecase.CountPersonUseCase;

@Value
@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class CountPersonUseCaseImpl implements CountPersonUseCase {
    PersonGateway personGateway;

    @Override
    public Uni<Long> count() {
        return personGateway.count();
    }
}
