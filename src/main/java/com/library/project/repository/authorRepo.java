package com.library.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.model.Author;

@Repository
public interface authorRepo extends JpaRepository<Author, Long> {

}
