package org.acme.domain.usecase;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.EPerson;

public interface FindPersonByIdUseCase {

    Uni<EPerson> execute(String id);
}
