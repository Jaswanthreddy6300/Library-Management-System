package com.library.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.globalException.BorrowerNotFoundException;
import com.library.project.model.BorrowRecord;
import com.library.project.model.Borrower;
import com.library.project.repository.borrowerRecord;
import com.library.project.repository.borrowerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class borrowerService {

    private final borrowerRepo brRepo;
    private final borrowerRecord recordRepo;

    public Borrower createBorrower(Borrower borrower) {
        return brRepo.save(borrower);
    }

    public List<Borrower> getAllBorrowers() {
        return brRepo.findAll();
    }

    public Borrower getBorrowerById(Long id) {
        return brRepo.findById(id)
                .orElseThrow(() -> new BorrowerNotFoundException(
                        "Borrower " + id + " Not Found"));
    }

    public Borrower updateBorrowerById(Long id,
            Borrower borrower) {

        Borrower existingBorrower = brRepo.findById(id)
                .orElseThrow(() -> new BorrowerNotFoundException(
                        "Borrower " + id + " Not Found"));

        existingBorrower.setName(borrower.getName());
        existingBorrower.setEmail(borrower.getEmail());
        existingBorrower.setPhoneNumber(
                borrower.getPhoneNumber());

        return brRepo.save(existingBorrower);
    }

    public void deleteBorrowerById(Long id) {

        Borrower borrower = brRepo.findById(id)
                .orElseThrow(() -> new BorrowerNotFoundException(
                        "Borrower " + id + " Not Found"));

        brRepo.delete(borrower);
    }

    public List<BorrowRecord> getBorrowRecordsByBorrowerId(
            Long borrowerId) {

        if (!brRepo.existsById(borrowerId)) {
            throw new BorrowerNotFoundException(
                    "Borrower " + borrowerId + " Not Found");
        }

        return recordRepo.findAll()
                .stream()
                .filter(record -> record.getBorrower()
                        .getId()
                        .equals(borrowerId))
                .toList();
    }
}