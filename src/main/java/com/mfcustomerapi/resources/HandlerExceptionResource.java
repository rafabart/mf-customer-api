package com.mfcustomerapi.resources;

import com.mfcustomerapi.exceptions.CustomerNotFoundException;
import com.mfcustomerapi.exceptions.EmailIntegrityViolationException;
import com.mfcustomerapi.exceptions.EmailNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@ControllerAdvice
public class HandlerExceptionResource {

    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<Object> objectNotFound(CustomerNotFoundException ex) {
        log.error("objectNotFound: " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }


    @ExceptionHandler(value = {EmailNotFoundException.class})
    public ResponseEntity<Object> objectNotFound(EmailNotFoundException ex) {
        log.error("objectNotFound: " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }


    @ExceptionHandler(value = {EmailIntegrityViolationException.class})
    public ResponseEntity<Object> integrityViolationException(EmailIntegrityViolationException ex) {
        log.error("validationError: " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> validationError(MethodArgumentNotValidException ex) {
        final Map<String, String> errorsByField = mapValidationErrorsByField(ex.getBindingResult().getAllErrors());
        log.error("validationError: " + errorsByField.toString());
        return new ResponseEntity<>(errorsByField, UNPROCESSABLE_ENTITY);
    }


    private Map<String, String> mapValidationErrorsByField(final List<ObjectError> objectErrors) {

        final Map<String, String> validationError = new HashMap<>();
        objectErrors.forEach(
                objectError -> validationError.put(((FieldError) objectError).getField(), objectError.getDefaultMessage())
        );

        return validationError;
    }
}
