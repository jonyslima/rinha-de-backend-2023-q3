package org.acme.app.kafka.deserializer;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.app.rest.dto.request.PersonRequest;

public class PersonDeserializer extends ObjectMapperDeserializer<PersonRequest> {
    public PersonDeserializer() {
        super(PersonRequest.class);
    }
}
