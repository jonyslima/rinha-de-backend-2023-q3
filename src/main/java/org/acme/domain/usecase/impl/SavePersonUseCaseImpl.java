package org.acme.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.model.EPerson;
import org.acme.domain.usecase.SavePersonUseCase;

@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class SavePersonUseCaseImpl implements SavePersonUseCase {
    PersonGateway personGateway;

    @Override
    public Uni<EPerson> execute(EPerson person) {
        return personGateway.save(person);
    }
}
