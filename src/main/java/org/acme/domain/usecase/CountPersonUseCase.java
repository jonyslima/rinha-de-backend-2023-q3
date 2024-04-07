package org.acme.domain.usecase;

import io.smallrye.mutiny.Uni;

public interface CountPersonUseCase {
    Uni<Long> execute();
}
