package com.library.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.project.model.BorrowRecord;
import com.library.project.service.borrowerRecordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/borrow-records")
@RequiredArgsConstructor
public class borrowerRecordController {

    private final borrowerRecordService service;

    @PostMapping("/borrow")
    public BorrowRecord borrowBook(
            @RequestParam Long borrowerId,
            @RequestParam Long bookId) {

        return service.borrowBook(
                borrowerId,
                bookId);
    }

    @PostMapping("/return/{recordId}")
    public BorrowRecord returnBook(
            @PathVariable Long recordId) {

        return service.returnBook(recordId);
    }

    @GetMapping
    public List<BorrowRecord> getAllBorrowRecords() {
        return service.getAllBorrowRecords();
    }

    @GetMapping("/{id}")
    public BorrowRecord getBorrowRecordById(
            @PathVariable Long id) {

        return service.getBorrowRecordById(id);
    }
}