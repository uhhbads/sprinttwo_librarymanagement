package com.practice.sprinttwo_librarymanagement.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publisher")
@Getter
@Setter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publisherName;

    @ManyToMany(mappedBy = "publishers", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();
}
