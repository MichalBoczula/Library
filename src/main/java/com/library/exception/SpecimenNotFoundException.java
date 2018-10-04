package com.library.exception;

import java.util.function.Supplier;

public class SpecimenNotFoundException extends RuntimeException {
    public SpecimenNotFoundException(Long id) {
        super("Could not found Specimen with id:" + id);
    }
}
