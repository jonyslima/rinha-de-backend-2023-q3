package org.acme.app.rest.dto.reponse;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@RegisterForReflection
public class PersonResponse {
    String id;
    String nickname;
    String name;
    LocalDate dateOfBirth;
    Set<String> stack;
}