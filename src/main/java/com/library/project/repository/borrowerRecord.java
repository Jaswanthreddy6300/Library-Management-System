package com.library.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.model.BorrowRecord;

@Repository
public interface borrowerRecord extends JpaRepository<BorrowRecord, Long> {

}
