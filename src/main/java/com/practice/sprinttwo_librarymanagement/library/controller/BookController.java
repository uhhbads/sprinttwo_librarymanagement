package com.practice.sprinttwo_librarymanagement.library.controller;

import com.practice.sprinttwo_librarymanagement.library.dto.BookRequest;
import com.practice.sprinttwo_librarymanagement.library.dto.BookResponse;
import com.practice.sprinttwo_librarymanagement.library.entity.Book;
import com.practice.sprinttwo_librarymanagement.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> postBook(@RequestBody BookRequest request){
        Book book = bookService.createBook(request);

        BookResponse response = mapToBookResponse(book);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/author/{authorId}")
    public List<BookResponse> getBooksByAuthor(@PathVariable Long authorId){
        return bookService.getBooksByAuthor(authorId)
                .stream()
                .map(this::mapToBookResponse)
                .toList();
    }

    @GetMapping("/search")
    public List<BookResponse> getBooksFromTitle(@RequestParam("title") String title){
        return bookService.searchBooks(title)
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
        response.setAuthorName(book.getAuthor().getName());
        return response;
    }
}
