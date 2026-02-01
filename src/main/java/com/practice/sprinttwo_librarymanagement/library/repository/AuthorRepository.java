package com.practice.sprinttwo_librarymanagement.library.repository;

import com.practice.sprinttwo_librarymanagement.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
