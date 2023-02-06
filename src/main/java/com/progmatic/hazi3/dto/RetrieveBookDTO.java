package com.progmatic.hazi3.dto;

import com.progmatic.hazi3.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class RetrieveBookDTO {

    Long id;

    private String title;

    private RetrieveAuthorDTO author;

    private String isbn;

    private boolean isActive;

    @Override
    public String toString() {
        return "id: " + id + "\ttitle: " + title + "\tauthor: " + author.getName() + "\t isbn: " + isbn + "\tisActive: " + isActive;
    }
}
