package com.bogdanadrian.ppmtool.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorValidationService {

    /**
     * @param bindingResult BindingResult
     * @return Map where the key is the field where the error occurred and the value is the default message.
     */
    public ResponseEntity<?> mapValidationService(final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Map<String, String>>(processFieldErrors(bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    /**
     * @param fieldErrors List with the field errors of the binding result object.
     * @return Map where the key is the field where the error occurred and the value is the default message.
     */
    private Map<String, String> processFieldErrors(final List<FieldError> fieldErrors) {
        final Map<String, String> errorMessages = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorMessages;
    }
}
