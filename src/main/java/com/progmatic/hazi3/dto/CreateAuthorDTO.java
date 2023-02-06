package com.progmatic.hazi3.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateAuthorDTO {

    @NonNull
    String name;

    @NonNull LocalDate birthDate;
//    List<BookDTO> books;

}
