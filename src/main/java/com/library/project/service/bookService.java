package com.library.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import com.library.project.globalException.BookNotFoundException;
import com.library.project.model.Author;
import com.library.project.model.Book;
import com.library.project.repository.authorRepo;
import com.library.project.repository.bookRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class bookService {

    private final bookRepo bRepo;

    @PersistenceContext
    private EntityManager entityManager;

    // Non_Repetable Read Testing
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void testNonRepeatableRead(
            Long bookId)
            throws InterruptedException {

        Book firstRead = bRepo.findById(bookId)
                .orElseThrow();

        System.out.println(
                "FIRST READ = "
                        + firstRead.getAvailableCopies());

        System.out.println(
                "Sleeping 30 seconds...");

        Thread.sleep(30000);

        entityManager.clear();

        Book secondRead = bRepo.findById(bookId)
                .orElseThrow();

        System.out.println(
                "SECOND READ = "
                        + secondRead.getAvailableCopies());
    }

    public Book createBook(Book book) {
        return bRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bRepo.findAll();
    }

    public Book getBookById(Long id) {
        return bRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book " + id + " Not Found"));
    }

    public Book updateBookById(Long id, Book book) {

        Book existingBook = bRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book " + id + " Not Found"));

        existingBook.setTitle(book.getTitle());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setTotalCopies(book.getTotalCopies());
        existingBook.setAvailableCopies(book.getAvailableCopies());
        existingBook.setAuthor(book.getAuthor());

        return bRepo.save(existingBook);
    }

    public void deleteBookById(Long id) {

        Book book = bRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book " + id + " Not Found"));

        bRepo.delete(book);
    }

    public List<Book> searchBooks(String title) {

        return bRepo.findAll()
                .stream()
                .filter(book -> book.getTitle()
                        .equalsIgnoreCase(title))
                .toList();
    }

    @Transactional
    public void updateCopies(
            Long bookId,
            Integer copies) {

        Book book = bRepo.findById(bookId)
                .orElseThrow();

        book.setAvailableCopies(copies);

        System.out.println(
                "UPDATED TO = "
                        + copies);
    }
}