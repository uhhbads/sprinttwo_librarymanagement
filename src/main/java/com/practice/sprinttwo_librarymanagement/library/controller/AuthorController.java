package com.practice.sprinttwo_librarymanagement.library.controller;

import com.practice.sprinttwo_librarymanagement.library.dto.AuthorRequest;
import com.practice.sprinttwo_librarymanagement.library.dto.AuthorResponse;
import com.practice.sprinttwo_librarymanagement.library.entity.Author;
import com.practice.sprinttwo_librarymanagement.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> postAuthor(@RequestBody @Valid AuthorRequest request){
        Author savedAuthor = authorService.createAuthor(request);

        AuthorResponse response = new AuthorResponse();
        response.setId(savedAuthor.getId());
        response.setName(savedAuthor.getName());
        response.setCountry(savedAuthor.getCountry());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("{id}")
    public Author getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }
}
