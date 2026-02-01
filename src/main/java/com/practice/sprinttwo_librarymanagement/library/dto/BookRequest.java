package com.practice.sprinttwo_librarymanagement.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Long authorId;
}
