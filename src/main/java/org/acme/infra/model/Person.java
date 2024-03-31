package org.acme.infra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@NamedQuery(name = "Person.findByIdWithStack", query = "FROM Person p LEFT JOIN FETCH p.stack s WHERE p.id = :id")
@NamedQuery(name = "Person.findByTerm", query = "SELECT DISTINCT p FROM Person p JOIN p.stack s WHERE p.nickname like :term or p.name like :term or s.name like :term")
public class Person {
    @Id
    UUID id;
    @Column(unique = true, length = 32, nullable = false)
    String nickname;
    @Column(length = 100, nullable = false)
    String name;
    @Column(nullable = false)
    LocalDate dateOfBirth;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Stack> stack = List.of();
}

