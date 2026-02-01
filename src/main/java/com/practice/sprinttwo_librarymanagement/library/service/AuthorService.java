package com.practice.sprinttwo_librarymanagement.library.service;

import com.practice.sprinttwo_librarymanagement.library.dto.AuthorRequest;
import com.practice.sprinttwo_librarymanagement.library.dto.AuthorResponse;
import com.practice.sprinttwo_librarymanagement.library.dto.BookResponse;
import com.practice.sprinttwo_librarymanagement.library.dto.BookSummary;
import com.practice.sprinttwo_librarymanagement.library.entity.Author;
import com.practice.sprinttwo_librarymanagement.library.entity.Book;
import com.practice.sprinttwo_librarymanagement.library.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author createAuthor(AuthorRequest request){
        Author author = new Author(request.getName(), request.getCountry());
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public List<AuthorResponse> getAllAuthors(boolean includeBooks){
        List<Author> authors = includeBooks
                ? authorRepository.findAllWithBooks()
                : authorRepository.findAll();

        return authors.stream()
                .map(author -> mapToAuthorResponse(author, includeBooks))
                .toList();
    }

    public Author getAuthorEntityById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public AuthorResponse getAuthorById(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("No author with id " + id + " found")
                );
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setCountry(author.getCountry());

        List<BookSummary> books = author.getBooks().stream()
                .map(book -> {
                    BookSummary dto = new BookSummary();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setIsbn(book.getIsbn());
                    dto.setPublicationYear(book.getPublicationYear());
                    return dto;
                })
                .toList();
        response.setBooks(books);

        return response;
    }

    private AuthorResponse mapToAuthorResponse(Author author, boolean includeBooks) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setCountry(author.getCountry());

        if (includeBooks) {
            response.setBooks(
                    author.getBooks().stream()
                            .map(book -> {
                                BookSummary dto = new BookSummary();
                                dto.setId(book.getId());
                                dto.setTitle(book.getTitle());
                                dto.setIsbn(book.getIsbn());
                                dto.setPublicationYear(book.getPublicationYear());
                                return dto;
                            })
                            .toList()
            );
        }

        return response;
    }
}
