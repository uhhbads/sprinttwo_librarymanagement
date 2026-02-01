package com.practice.sprinttwo_librarymanagement.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSummary {
    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
}

