package org.acme.cross.mappers;

import org.acme.app.rest.dto.reponse.PersonResponse;
import org.acme.app.rest.dto.request.PersonRequest;
import org.acme.domain.model.EPerson;
import org.acme.domain.model.EStack;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PersonMapper {

    EPerson toPerson(PersonRequest personRequest);

    PersonResponse toPerson(EPerson person);

    List<PersonResponse> toPerson(List<EPerson> person);

    @Mapping(target = "name")
    EStack toStack(String name);

    default String toStack(EStack stack) {
        if (stack == null) {
            return null;
        }

        return stack.getName();
    }

}
