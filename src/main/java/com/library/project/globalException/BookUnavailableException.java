package com.library.project.globalException;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(String msg) {
        super(msg);
    }
}
