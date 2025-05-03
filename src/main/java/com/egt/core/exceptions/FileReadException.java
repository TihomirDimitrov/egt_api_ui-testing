package com.egt.core.exceptions;

/**
 * Custom unchecked exception that indicates a failure to read a file.
 */
public class FileReadException extends RuntimeException {

    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
