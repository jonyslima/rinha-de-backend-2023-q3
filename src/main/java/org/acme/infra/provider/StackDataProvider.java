package org.acme.infra.provider;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.acme.domain.gateway.StackGateway;
import org.acme.domain.model.EStack;
import org.acme.infra.provider.mappers.StackMapper;
import org.acme.infra.repository.StackRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Value
@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
public class StackDataProvider implements StackGateway {
    StackRepository stackRepository;
    StackMapper stackMapper;

    @Override
    public Uni<List<EStack>> findStackByIdPerson(Set<UUID> ids) {
        return stackRepository.findStackByIdPerson(ids).map(stackMapper::toStackWithOnlyIdPerson);
    }
}
