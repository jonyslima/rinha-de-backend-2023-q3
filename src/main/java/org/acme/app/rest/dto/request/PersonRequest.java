package org.acme.app.rest.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class PersonRequest{
    @NotBlank
    @Size(max = 32)
    @JsonProperty("apelido")
    private String nickname;
    @NotBlank
    @Size(max = 100)
    @JsonProperty("nome")
    private String name;
    @NotNull
    @JsonProperty("nascimento")
    private LocalDate dateOfBirth;
    @JsonProperty("stack")
    private Set<@NotBlank @Size(max = 32) String> stack;
}
