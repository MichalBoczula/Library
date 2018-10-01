package com.library.exception;

public class ReaderNotFoundException extends RuntimeException {
    public ReaderNotFoundException(Long id) {
        super("Could not found Book with id:" + id);
    }
}
