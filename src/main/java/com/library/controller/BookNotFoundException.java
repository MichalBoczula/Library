package com.library.controller;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id){
        super("Could not found Book with id:" + id);
    }
}
