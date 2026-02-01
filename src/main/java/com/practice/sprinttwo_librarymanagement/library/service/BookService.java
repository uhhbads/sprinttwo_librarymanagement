package com.practice.sprinttwo_librarymanagement.library.service;

import com.practice.sprinttwo_librarymanagement.library.dto.BookRequest;
import com.practice.sprinttwo_librarymanagement.library.dto.BookResponse;
import com.practice.sprinttwo_librarymanagement.library.entity.Author;
import com.practice.sprinttwo_librarymanagement.library.entity.Book;
import com.practice.sprinttwo_librarymanagement.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book createBook(BookRequest bookRequest){
        Author author = authorService.getAuthorById(bookRequest.getAuthorId());
        Book book = new Book(
                bookRequest.getTitle(),
                bookRequest.getIsbn(),
                bookRequest.getPublicationYear(),
                author);
        return bookRepository.save(book);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToBookResponse)
                .toList();
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

    public List<Book> getBooksByAuthor(Long authorId){
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> searchBooks(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}
