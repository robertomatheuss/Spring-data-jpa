package com.bookstore.jpa.controller.exceptions;

public class BookException extends RuntimeException{

    public BookException() {
    }

    public BookException(String message) {
        super(message);
    }

}
