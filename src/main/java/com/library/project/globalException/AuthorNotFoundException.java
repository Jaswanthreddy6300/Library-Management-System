package com.library.project.globalException;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String msg) {
        super(msg);
    }
}
