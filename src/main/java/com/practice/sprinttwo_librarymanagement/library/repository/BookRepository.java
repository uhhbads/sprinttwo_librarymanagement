package com.practice.sprinttwo_librarymanagement.library.repository;

import com.practice.sprinttwo_librarymanagement.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
