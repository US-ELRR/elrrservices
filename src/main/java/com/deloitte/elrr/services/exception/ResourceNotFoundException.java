package com.deloitte.elrr.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     * @param message
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
