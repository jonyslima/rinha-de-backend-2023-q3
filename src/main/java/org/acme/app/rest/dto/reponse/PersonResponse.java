package org.acme.app.rest.dto.reponse;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class PersonResponse {
    String id;
    String nickname;
    String name;
    LocalDate dateOfBirth;
    Set<String> stack;
}