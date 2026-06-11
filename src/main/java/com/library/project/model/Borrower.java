package com.library.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "borrowers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY)
    private List<BorrowRecord> borrowRecords = new ArrayList<>();
}