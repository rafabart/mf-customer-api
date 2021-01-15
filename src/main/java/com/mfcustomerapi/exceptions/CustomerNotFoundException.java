package com.mfcustomerapi.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Cliente não encontrado";

    public CustomerNotFoundException() {
        super(MESSAGE);
    }

    public CustomerNotFoundException(final Throwable cause) {
        super(MESSAGE, cause);
    }
}
