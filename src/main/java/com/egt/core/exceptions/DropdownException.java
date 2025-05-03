package com.egt.core.exceptions;

/**
 * Custom unchecked exception that indicates a failure to get dropdown options.
 */
public class DropdownException extends RuntimeException {
    public DropdownException(String message) {
        super(message);
    }
}

