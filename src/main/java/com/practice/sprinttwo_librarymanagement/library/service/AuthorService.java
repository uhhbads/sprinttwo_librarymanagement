package com.practice.sprinttwo_librarymanagement.library.service;

import com.practice.sprinttwo_librarymanagement.library.dto.AuthorRequest;
import com.practice.sprinttwo_librarymanagement.library.entity.Author;
import com.practice.sprinttwo_librarymanagement.library.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("No author with id " + id + " found")
                );
    }
}
