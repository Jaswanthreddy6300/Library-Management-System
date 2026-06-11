package com.library.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.model.Book;

@Repository
public interface bookRepo extends JpaRepository<Book, Long> {

}
