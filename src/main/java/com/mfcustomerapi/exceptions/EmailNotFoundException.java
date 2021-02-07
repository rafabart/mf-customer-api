package com.mfcustomerapi.exceptions;

public class EmailNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Email não encontrado -> email: ";

    public EmailNotFoundException(final String email) {
        super(MESSAGE + email.substring(0, 5));
    }

    public EmailNotFoundException(final String email, final Throwable cause) {
        super(MESSAGE + email.substring(0, 5), cause);
    }
}
