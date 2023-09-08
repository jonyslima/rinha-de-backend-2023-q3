package org.acme.domain.usecase;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.EPerson;

import java.util.List;

public interface FindPersonByTermUseCase {

    public Uni<List<EPerson>> execute(String term);
}
