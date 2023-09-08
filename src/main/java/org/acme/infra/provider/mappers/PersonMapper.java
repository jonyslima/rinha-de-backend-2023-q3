package org.acme.infra.provider.mappers;

import org.acme.domain.model.EPerson;
import org.acme.domain.model.EStack;
import org.acme.infra.model.Person;
import org.acme.infra.model.Stack;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PersonMapper {

    public Person toPerson(EPerson person);

    public EPerson toPerson(Person person);

    @Named(value = "withoutStack")
    @Mapping(target = "stack", ignore = true)
    public EPerson toPersonWithoutStack(Person person);

    @IterableMapping(qualifiedByName = "withoutStack")
    public List<EPerson> toPersonWithoutStack(List<Person> person);


    @Mapping(target = "person", ignore = true)
    public Stack toStack(EStack stack);

    @Mapping(target = "person", ignore = true)
    public EStack toStack(Stack stack);
}
