package com.mfcustomerapi.exceptions;

public class EmailIntegrityViolationException extends RuntimeException {

    private static final String MESSAGE = "Email já cadastrado -> email: ";

    public EmailIntegrityViolationException(final String email) {
        super(MESSAGE + email.substring(0, 5));
    }

    public EmailIntegrityViolationException(final String email, final Throwable cause) {
        super(MESSAGE + email.substring(0, 5), cause);
    }
}
