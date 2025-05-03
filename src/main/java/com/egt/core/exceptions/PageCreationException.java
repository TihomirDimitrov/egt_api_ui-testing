package com.egt.core.exceptions;

/**
 * Custom unchecked exception that indicates a failure during page object creation.
 */
public class PageCreationException extends RuntimeException {
    public PageCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}