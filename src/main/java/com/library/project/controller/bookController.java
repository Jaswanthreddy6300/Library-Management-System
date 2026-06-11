package com.library.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.project.model.Author;
import com.library.project.model.Book;
import com.library.project.service.bookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class bookController {

    private final bookService service;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    // Eager loading Example
    // @GetMapping("/lazy-test/{id}")
    // public String lazyTest(@PathVariable Long id) {

    // Book book = service.getBookById(id);

    // System.out.println("BOOK FETCHED");

    // return book.getTitle();
    // }

    // // Lazy Loading Example

    // @GetMapping("/lazy-test/{id}")
    // public String lazyTest(@PathVariable Long id) {

    // Book book = service.getBookById(id);

    // System.out.println("BOOK FETCHED");

    // String authorName = book.getAuthor().getName();

    // return authorName;
    // }

    // Proxy Object

    @GetMapping("/proxy/{id}")
    public String proxyTest(@PathVariable Long id) {

        Book book = service.getBookById(id);

        System.out.println(book.getAuthor().getClass());

        return "Check Console";
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        Book b = service.getBookById(id);
        return b;
    }

    // lazy initialization exception
    // @GetMapping("/lazy-error/{id}")
    // public String lazyError(@PathVariable Long id) {

    // Book book = service.getBookById(id);

    // Author author = book.getAuthor();

    // return author.getName();
    // }

    @PutMapping("/{id}")
    public Book updateBookById(
            @PathVariable Long id,
            @RequestBody Book book) {

        return service.updateBookById(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        service.deleteBookById(id);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam String title) {

        return service.searchBooks(title);
    }

    // Non repeatable testing

    @GetMapping("/non-repeatable-read/{id}")
    public String testNonRepeatableRead(
            @PathVariable Long id)
            throws Exception {

        service.testNonRepeatableRead(id);

        return "Done";
    }

    @PutMapping("/update/{id}/{copies}")
    public String update(
            @PathVariable Long id,
            @PathVariable Integer copies) {

        service.updateCopies(id, copies);

        return "Updated";
    }
}