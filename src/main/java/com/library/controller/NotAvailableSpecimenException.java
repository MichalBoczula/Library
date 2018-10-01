package com.library.controller;

public class NotAvailableSpecimenException extends RuntimeException {
    public NotAvailableSpecimenException() {
        super("All specimens of this book are not available");
    }
}
