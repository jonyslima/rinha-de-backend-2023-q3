package org.acme.domain.model;

import lombok.Data;
import org.acme.infra.model.Person;

import java.util.UUID;

@Data
public class EStack {
    UUID id;
    String name;
    EPerson person;
}
