package com.library.project.globalException;

public class BorrowRecordNotFoundException extends RuntimeException {
    public BorrowRecordNotFoundException(String msg) {
        super(msg);
    }
}
