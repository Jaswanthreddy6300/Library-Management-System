package com.library.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.project.model.BorrowRecord;
import com.library.project.model.Borrower;
import com.library.project.service.borrowerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/borrowers")
@RequiredArgsConstructor
public class borrowerController {

    private final borrowerService service;

    @PostMapping
    public Borrower createBorrower(
            @RequestBody Borrower borrower) {

        return service.createBorrower(borrower);
    }

    @GetMapping
    public List<Borrower> getAllBorrowers() {
        return service.getAllBorrowers();
    }

    @GetMapping("/{id}")
    public Borrower getBorrowerById(
            @PathVariable Long id) {

        return service.getBorrowerById(id);
    }

    @PutMapping("/{id}")
    public Borrower updateBorrowerById(
            @PathVariable Long id,
            @RequestBody Borrower borrower) {

        return service.updateBorrowerById(
                id,
                borrower);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrowerById(
            @PathVariable Long id) {

        service.deleteBorrowerById(id);
    }

    @GetMapping("/{id}/records")
    public List<BorrowRecord> getBorrowRecordsByBorrowerId(
            @PathVariable Long id) {

        return service.getBorrowRecordsByBorrowerId(id);
    }
}