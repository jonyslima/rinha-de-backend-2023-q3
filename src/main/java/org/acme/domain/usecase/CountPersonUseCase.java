package org.acme.domain.usecase;

import io.smallrye.mutiny.Uni;

public interface CountPersonUseCase {
    public Uni<Long> count();
}
