package com.library.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.model.Borrower;

@Repository
public interface borrowerRepo extends JpaRepository<Borrower, Long> {

}
