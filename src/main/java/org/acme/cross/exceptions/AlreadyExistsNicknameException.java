package org.acme.cross.exceptions;

public class AlreadyExistsNicknameException extends Exception {
    public AlreadyExistsNicknameException(String message) {
        super(message);
    }
}
