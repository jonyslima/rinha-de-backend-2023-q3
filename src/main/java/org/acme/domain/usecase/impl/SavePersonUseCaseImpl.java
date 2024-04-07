package org.acme.domain.usecase.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.acme.cross.exceptions.AlreadyExistsNicknameException;
import org.acme.domain.gateway.PersonGateway;
import org.acme.domain.model.EPerson;
import org.acme.domain.usecase.SavePersonUseCase;

import java.text.MessageFormat;

@ApplicationScoped
@AllArgsConstructor
public class SavePersonUseCaseImpl implements SavePersonUseCase {
    PersonGateway personGateway;

    @Override
    public Uni<EPerson> execute(EPerson person) {
        return personGateway.existsByNickname(person.getNickname()).onItem().transformToUni(exists -> {
            if (exists) {

                return Uni.createFrom()
                        .failure(new AlreadyExistsNicknameException(MessageFormat.format("already exists nickname: {0}", person.getNickname())));
            }

            return personGateway.save(person);
        });
    }
}
