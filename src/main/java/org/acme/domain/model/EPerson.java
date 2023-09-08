package org.acme.domain.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class EPerson {
    private UUID id;
    private String nickname;
    private String name;
    private LocalDate dateOfBirth;
    private Set<EStack> stack = new HashSet<>();

    public void addStack(EStack stack) {
        this.stack.add(stack);
    }

}
