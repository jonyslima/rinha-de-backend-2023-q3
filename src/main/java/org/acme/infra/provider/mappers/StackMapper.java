package org.acme.infra.provider.mappers;

import org.acme.domain.model.EPerson;
import org.acme.domain.model.EStack;
import org.acme.infra.model.Person;
import org.acme.infra.model.Stack;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface StackMapper {

    @Named("stackOnlyIdPerson")
    @BeanMapping(qualifiedByName = "onlyIdPerson")
    public EStack toStackWithOnlyIdPerson(Stack stack);
    default public EPerson toPerson(Person person) {
        if (person == null)
            return null;

        EPerson ePerson = new EPerson();
        ePerson.setId(person.getId());
        return ePerson;
    }

    @IterableMapping(qualifiedByName = "stackOnlyIdPerson")
    public List<EStack> toStackWithOnlyIdPerson(List<Stack> stacks);
}
