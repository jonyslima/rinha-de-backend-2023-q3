package org.acme.app.rest.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonRequest {
    UUID id;
    @NotBlank
    @Size(max = 32)
    @JsonProperty("apelido")
    String nickname;
    @NotBlank
    @Size(max = 100)
    @JsonProperty("nome")
    String name;
    @NotNull
    @JsonProperty("nascimento")
    LocalDate dateOfBirth;
    @JsonProperty("stack")
    Set<@NotBlank @Size(max = 32) String> stack;
}
