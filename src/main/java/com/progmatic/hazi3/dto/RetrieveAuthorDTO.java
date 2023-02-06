package com.progmatic.hazi3.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RetrieveAuthorDTO {

    private Long id;

    private String name;

    private LocalDate birthDate;

    @Override
    public String toString() {
        return "id: " + id + "\tname: " + name + "\tbirthdate: " + birthDate;
    }
}
