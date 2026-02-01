package com.practice.sprinttwo_librarymanagement.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(?:\\d[- ]*){9}[\\dX]$", message = "Invalid ISBN-10 format")
    private String isbn;

    @NotNull(message = "Publication year is required")
    private Integer publicationYear;

    @NotNull(message = "Author ID is required")
    private Long authorId;
}
