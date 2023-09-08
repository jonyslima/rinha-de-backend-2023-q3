package org.acme.infra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@NamedQuery(name = "Stack.findByIdPerson", query = "FROM Stack s JOIN s.person p WHERE p.id in :ids")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Stack {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    UUID id;
    @Column(nullable = false, length = 32)
    String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personId", nullable = false, updatable = false, insertable = true)
    Person person;
}
