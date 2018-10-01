package com.library.exception;

public class SpecimenNotFoundException extends RuntimeException {
    public SpecimenNotFoundException(Long id) {
        super("Could not found Specimen with id:" + id);
    }
}
