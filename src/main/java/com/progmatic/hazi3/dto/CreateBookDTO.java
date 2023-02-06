package com.progmatic.hazi3.dto;

import com.progmatic.hazi3.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateBookDTO {

    @NonNull
    private String title;

    @NonNull
    private RetrieveAuthorDTO author;

    @NonNull
    private String isbn;

    @NonNull
    private boolean isActive;

}
