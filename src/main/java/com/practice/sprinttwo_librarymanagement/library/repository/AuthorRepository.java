package com.practice.sprinttwo_librarymanagement.library.repository;

import com.practice.sprinttwo_librarymanagement.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("""
    SELECT DISTINCT a
    FROM Author a
    LEFT JOIN FETCH a.books
    """)
    List<Author> findAllWithBooks();
    Optional<Author> findByName(String name);
}
