package org.acme.infra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@NamedQuery(name = "Stack.findByIdPerson", query = "FROM Stack s JOIN s.person p WHERE p.id in :ids")
public class Stack {
    @Id
    UUID id;
    @Column(nullable = false, length = 32)
    String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personId", nullable = false, updatable = false, insertable = true)
    Person person;
}
