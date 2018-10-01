package com.library.exception;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException(Long id) {
        super("Could not found Book with id:" + id);
    }
}
