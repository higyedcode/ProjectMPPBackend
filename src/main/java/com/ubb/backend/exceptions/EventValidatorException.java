package com.ubb.backend.exceptions;

import java.util.List;

public class EventValidatorException extends Exception {
    public EventValidatorException(List<String> errors) {
        super(String.join(", ", errors));
    }
}