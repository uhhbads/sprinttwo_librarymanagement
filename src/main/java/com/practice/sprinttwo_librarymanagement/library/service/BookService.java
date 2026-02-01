package com.practice.sprinttwo_librarymanagement.library.service;

import com.practice.sprinttwo_librarymanagement.library.dto.BookRequest;
import com.practice.sprinttwo_librarymanagement.library.dto.BookResponse;
import com.practice.sprinttwo_librarymanagement.library.entity.Author;
import com.practice.sprinttwo_librarymanagement.library.entity.Book;
import com.practice.sprinttwo_librarymanagement.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Transactional
    public Book createBook(BookRequest bookRequest){
        Author author = authorService.getAuthorEntityById(bookRequest.getAuthorId());
        Book book = new Book(
                bookRequest.getTitle(),
                bookRequest.getIsbn(),
                bookRequest.getPublicationYear(),
                author);
        return bookRepository.save(book);
    }

    public List<BookResponse> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = bookRepository.findAll(pageable);

        return booksPage.stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> getBooksByAuthor(Long authorId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = bookRepository.findByAuthorId(authorId, pageable);

        return booksPage.stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> searchBooks(String title, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = bookRepository.findByTitleContainingIgnoreCase(title, pageable);

        return booksPage.stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    private BookResponse mapToBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setIsbn(book.getIsbn());
        response.setPublicationYear(book.getPublicationYear());

        response.setAuthorName(
                book.getAuthor() != null ? book.getAuthor().getName() : null
        );

        return response;
    }
}
