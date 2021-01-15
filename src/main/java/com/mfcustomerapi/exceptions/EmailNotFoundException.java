package com.mfcustomerapi.exceptions;

public class EmailNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Email não encontrado";

    public EmailNotFoundException() {
        super(MESSAGE);
    }

    public EmailNotFoundException(final Throwable cause) {
        super(MESSAGE, cause);
    }
}
