package com.library.project.globalException;

public class ConcurrentBookUpdateException extends RuntimeException {
    public ConcurrentBookUpdateException(
            String message) {
        super(message);
    }
}
