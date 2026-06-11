package com.library.project.globalException;

public class BorrowerNotFoundException extends RuntimeException {
    public BorrowerNotFoundException(String msg) {
        super(msg);
    }
}
