package com.library.project.controller;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import com.library.project.model.Author;
import com.library.project.model.Book;
import com.library.project.service.authorService;

import lombok.RequiredArgsConstructor;

@Profile("dev")
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class authorController {

    private final authorService service;

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return service.createAuthor(author);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return service.getAuthorById(id);
    }

    @PutMapping("/{id}")
    public Author updateAuthorById(
            @PathVariable Long id,
            @RequestBody Author author) {

        return service.updateAuthorById(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        service.deleteAuthorById(id);
    }

    @GetMapping("/{id}/books")
    public List<Book> getBooksByAuthorId(
            @PathVariable Long id) {

        return service.getBooksByAuthorId(id);
    }

    // N+1 Testing

    @GetMapping("/nplusone")
    public String testNPlusOne() {

        service.testNPlusOne();

        return "Check Console";
    }
}