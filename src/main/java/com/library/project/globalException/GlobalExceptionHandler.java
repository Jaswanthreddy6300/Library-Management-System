package com.library.project.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimisticLock(
            ObjectOptimisticLockingFailureException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Another user modified the book. Please retry.");
    }
}
