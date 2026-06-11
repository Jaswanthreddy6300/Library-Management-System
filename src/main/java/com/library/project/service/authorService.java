package com.library.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.project.globalException.AuthorNotFoundException;
import com.library.project.model.Author;
import com.library.project.model.Book;
import com.library.project.repository.authorRepo;
import com.library.project.repository.bookRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class authorService {

    private final authorRepo aRepo;
    private final bookRepo bRepo;

    public Author createAuthor(Author author) {
        return aRepo.save(author);
    }

    public List<Author> getAllAuthors() {
        return aRepo.findAll();
    }

    public Author getAuthorById(Long id) {
        return aRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author" + id + " not found"));
    }

    public Author updateAuthorById(Long id, Author author) {

        Author existingAuthor = aRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author " + id + " Not Found"));

        existingAuthor.setName(author.getName());
        existingAuthor.setEmail(author.getEmail());
        existingAuthor.setNationality(author.getNationality());

        return aRepo.save(existingAuthor);
    }

    public void deleteAuthorById(Long id) {
        Author author = aRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author" + id + " Not Found"));
        aRepo.delete(author);
    }

    public List<Book> getBooksByAuthorId(Long id) {
        List<Book> book = bRepo.findAll().stream().filter((b) -> b.getAuthor().getId().equals(id)).toList();
        return book;
    }

    // N+1 Testing

    @Transactional
    public void testNPlusOne() {

        List<Author> authors = aRepo.findAll();

        for (Author author : authors) {

            System.out.println(
                    author.getBooks().size());
        }
    }

}
