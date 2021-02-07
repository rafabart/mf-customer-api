package com.mfcustomerapi.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Cliente não encontrado -> id: ";

    public CustomerNotFoundException(final Long id) {
        super(MESSAGE + id);
    }

    public CustomerNotFoundException(final Long id, final Throwable cause) {
        super(MESSAGE + id, cause);
    }
}
