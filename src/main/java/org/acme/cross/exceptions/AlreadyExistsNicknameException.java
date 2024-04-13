package org.acme.cross.exceptions;

import java.text.MessageFormat;

public class AlreadyExistsNicknameException extends Exception {
    private static final String MESSAGE = "already exists nickname: {0}";

    public AlreadyExistsNicknameException(String nickname) {
        super(MessageFormat.format(MESSAGE, nickname));
    }
}
