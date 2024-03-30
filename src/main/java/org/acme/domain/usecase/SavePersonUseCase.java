package org.acme.domain.usecase;

import io.smallrye.mutiny.Uni;
import org.acme.domain.model.EPerson;

public interface SavePersonUseCase {

    public Uni<EPerson> execute(EPerson person);

}
