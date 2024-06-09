package org.acme.app.kafka;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acme.app.rest.dto.request.PersonRequest;
import org.acme.app.service.PersonService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
@AllArgsConstructor
public class PersonConsumer {
    PersonService personService;

    @Incoming("person-in")
    public Uni<Void> consume(PersonRequest personRequest) {
        LOG.info("ID: {}",personRequest.getNickname());
        return personService.save(personRequest).replaceWithVoid();
    }

}
