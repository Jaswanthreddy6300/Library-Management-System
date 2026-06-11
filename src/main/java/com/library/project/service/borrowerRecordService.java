package com.library.project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.globalException.BookNotFoundException;
import com.library.project.globalException.BookUnavailableException;
import com.library.project.globalException.BorrowRecordNotFoundException;
import com.library.project.globalException.BorrowerNotFoundException;
import com.library.project.model.Book;
import com.library.project.model.BorrowRecord;
import com.library.project.model.Borrower;
import com.library.project.repository.bookRepo;
import com.library.project.repository.borrowerRecord;
import com.library.project.repository.borrowerRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class borrowerRecordService {

        private final borrowerRecord recordRepo;
        private final borrowerRepo borrowerRepo;
        private final bookRepo bookRepo;

        // public BorrowRecord borrowBook(Long borrowerId,
        // Long bookId) {

        // Borrower borrower = borrowerRepo.findById(
        // borrowerId)
        // .orElseThrow(() -> new BorrowerNotFoundException(
        // "Borrower " + borrowerId
        // + " Not Found"));

        // Book book = bookRepo.findById(bookId)
        // .orElseThrow(() -> new BookNotFoundException(
        // "Book " + bookId
        // + " Not Found"));

        // if (book.getAvailableCopies() <= 0) {
        // throw new BookUnavailableException(
        // "Book is currently unavailable");
        // }

        // book.setAvailableCopies(
        // book.getAvailableCopies() - 1);

        // bookRepo.save(book);

        // BorrowRecord record = new BorrowRecord();

        // record.setBook(book);
        // record.setBorrower(borrower);
        // record.setBorrowDate(LocalDate.now());
        // record.setDueDate(LocalDate.now().plusDays(14));
        // record.setReturned(false);

        // return recordRepo.save(record);
        // }

        // //understand the concurrency Issues
        // @Transactional
        // public BorrowRecord borrowBook(Long borrowerId, Long bookId) {

        // Borrower borrower = borrowerRepo.findById(borrowerId)
        // .orElseThrow(() -> new RuntimeException("Borrower not found"));

        // Book book = bookRepo.findById(bookId)
        // .orElseThrow(() -> new RuntimeException("Book not found"));

        // System.out.println("\n==============================");
        // System.out.println("Thread : " + Thread.currentThread().getName());
        // System.out.println("Borrower : " + borrower.getName());
        // System.out.println("Available Copies BEFORE = "
        // + book.getAvailableCopies());

        // try {
        // Thread.sleep(15000); // 10 seconds
        // } catch (InterruptedException e) {
        // throw new RuntimeException(e);
        // }

        // if (book.getAvailableCopies() <= 0) {
        // throw new RuntimeException("Book not available");
        // }

        // book.setAvailableCopies(
        // book.getAvailableCopies() - 1);

        // System.out.println("Available Copies AFTER = "
        // + book.getAvailableCopies());

        // BorrowRecord record = BorrowRecord.builder()
        // .book(book)
        // .borrower(borrower)
        // .borrowDate(LocalDate.now())
        // .dueDate(LocalDate.now().plusDays(14))
        // .returned(false)
        // .build();

        // BorrowRecord savedRecord = recordRepo.save(record);

        // System.out.println("==============================\n");

        // return savedRecord;
        // }

        // implement the Optimistic locking

        @Transactional
        public BorrowRecord borrowBook(
                        Long borrowerId,
                        Long bookId) {

                Borrower borrower = borrowerRepo.findById(borrowerId)
                                .orElseThrow(() -> new RuntimeException("Borrower not found"));

                Book book = bookRepo.findById(bookId)
                                .orElseThrow(() -> new RuntimeException("Book not found"));

                System.out.println("\n==============================");
                System.out.println("Thread : "
                                + Thread.currentThread().getName());
                System.out.println("Borrower : "
                                + borrower.getName());

                System.out.println(
                                "Available Copies BEFORE = "
                                                + book.getAvailableCopies());

                try {
                        Thread.sleep(30000);
                } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                }

                if (book.getAvailableCopies() <= 0) {
                        throw new RuntimeException("Book not available");
                }

                book.setAvailableCopies(
                                book.getAvailableCopies() - 1);

                System.out.println(
                                "Available Copies AFTER = "
                                                + book.getAvailableCopies());

                BorrowRecord record = BorrowRecord.builder()
                                .book(book)
                                .borrower(borrower)
                                .borrowDate(LocalDate.now())
                                .dueDate(LocalDate.now().plusDays(14))
                                .returned(false)
                                .build();

                BorrowRecord savedRecord = recordRepo.save(record);

                System.out.println("==============================");

                return savedRecord;
        }

        public BorrowRecord returnBook(Long recordId) {

                BorrowRecord record = recordRepo.findById(
                                recordId)
                                .orElseThrow(() -> new BorrowRecordNotFoundException(
                                                "Record " + recordId
                                                                + " Not Found"));

                if (!record.getReturned()) {

                        record.setReturned(true);
                        record.setReturnDate(LocalDate.now());

                        Book book = record.getBook();

                        book.setAvailableCopies(
                                        book.getAvailableCopies() + 1);

                        bookRepo.save(book);
                }

                return recordRepo.save(record);
        }

        public List<BorrowRecord> getAllBorrowRecords() {
                return recordRepo.findAll();
        }

        public BorrowRecord getBorrowRecordById(Long id) {
                return recordRepo.findById(id)
                                .orElseThrow(() -> new BorrowRecordNotFoundException(
                                                "Record " + id
                                                                + " Not Found"));
        }
}