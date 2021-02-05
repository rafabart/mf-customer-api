package com.mfcustomerapi.exceptions;

public class EmailIntegrityViolationException extends RuntimeException {

    private static final String MESSAGE = "Email já cadastrado";

    public EmailIntegrityViolationException() {
        super(MESSAGE);
    }

    public EmailIntegrityViolationException(final Throwable cause) {
        super(MESSAGE, cause);
    }
}
