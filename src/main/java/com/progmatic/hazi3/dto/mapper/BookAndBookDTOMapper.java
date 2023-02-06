package com.progmatic.hazi3.dto.mapper;

import com.progmatic.hazi3.dto.CreateBookDTO;
import com.progmatic.hazi3.dto.RetrieveAuthorDTO;
import com.progmatic.hazi3.dto.RetrieveBookDTO;
import com.progmatic.hazi3.model.Author;
import com.progmatic.hazi3.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookAndBookDTOMapper {

    BookAndBookDTOMapper INSTANCE = Mappers.getMapper(BookAndBookDTOMapper.class);

    Book createBookDTOtoBook(CreateBookDTO source);

    CreateBookDTO bookToCreateBookDTO(Book source);

    RetrieveBookDTO bookToRetrieveBookDTO(Book source);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-mm-dd")
    Author retrieveAuthorDTOtoAuthor(RetrieveAuthorDTO source);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-mm-dd")
    RetrieveAuthorDTO authorToRetrieveAuthorDTO(Author source);

}
