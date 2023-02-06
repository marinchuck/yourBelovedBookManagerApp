package com.progmatic.hazi3.dto.mapper;

import com.progmatic.hazi3.dto.CreateAuthorDTO;
import com.progmatic.hazi3.dto.RetrieveAuthorDTO;
import com.progmatic.hazi3.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorAndAuthorDTOMapper {

    AuthorAndAuthorDTOMapper INSTANCE = Mappers.getMapper( AuthorAndAuthorDTOMapper.class );

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-mm-dd")
    Author createAuthorDTOtoAuthor(CreateAuthorDTO source);

    CreateAuthorDTO authortoCreateAuthorDTO(Author source);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-mm-dd")
    RetrieveAuthorDTO authorToRetrieveAuthorDTO(Author source);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-mm-dd")
    Author retrieveAuthorDTOtoAuthor(RetrieveAuthorDTO source);
}
