package org.acme.app.rest.dto.reponse;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class PersonResponse {
    private String id;
    private String nickname;
    private String name;
    private LocalDate dateOfBirth;
    private Set<String> stack;
}