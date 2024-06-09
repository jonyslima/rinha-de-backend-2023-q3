package org.acme.app.kafka.deserializer;

import io.quarkus.kafka.client.serialization.ObjectMapperSerializer;
import org.acme.app.rest.dto.request.PersonRequest;

public class PersonSerializer extends ObjectMapperSerializer<PersonRequest> {
}
