package org.acme.infra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@NamedQuery(name = "Person.findByTerm",query = "SELECT DISTINCT p FROM Person p JOIN p.stack s WHERE p.nickname like :term or p.name like :term or s.name like :term")
@Data
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, length = 32, nullable = false)
    private String nickname;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Stack> stack = Set.of();
}
