package com.paypal.bfs.test.employeeserv.exception;

import java.util.Collections;
import java.util.List;

public class EmployeeValidationFailedException extends RuntimeException {
    private List<String> errors;

    public EmployeeValidationFailedException(List<String> messages) {
        errors = messages;
    }

    public EmployeeValidationFailedException(String message) {
        errors = Collections.singletonList(message);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
