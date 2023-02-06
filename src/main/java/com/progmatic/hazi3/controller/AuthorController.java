package com.progmatic.hazi3.controller;

import com.progmatic.hazi3.dto.CreateAuthorDTO;
import com.progmatic.hazi3.dto.RetrieveAuthorDTO;
import com.progmatic.hazi3.service.AuthorService;
import com.progmatic.hazi3.util.JPAUtil;
import java.util.List;

public class AuthorController {

    private final AuthorService authorService;

    private AuthorController() {
        this.authorService = AuthorService.newInstance();
    }

    public static AuthorController newInstance() {
        return new AuthorController();
    }

    public void createAuthor(CreateAuthorDTO createAuthorDTO) {
        authorService.saveAuthor(createAuthorDTO);
        JPAUtil.closeEntityManager();
    }

    public List<RetrieveAuthorDTO> readAllAuthors() {
        List<RetrieveAuthorDTO> retrieveAuthorDTOList = authorService.readAllAuthors();
        JPAUtil.closeEntityManager();
        return retrieveAuthorDTOList;
    }


    public void updateAuthor(RetrieveAuthorDTO retrieveAuthorDTO, String[] params) {
        authorService.updateAuthor(retrieveAuthorDTO, params);
        JPAUtil.closeEntityManager();
    }


    public void deleteAuthorById(Long id) {
        authorService.deleteAuthorById(id);
        JPAUtil.closeEntityManager();
    }


    public RetrieveAuthorDTO retrieveAuthorById(long id) {
        RetrieveAuthorDTO retrieveAuthorDTO = authorService.retrieveAuthorById(id);
        JPAUtil.closeEntityManager();
        return retrieveAuthorDTO;
    }

    public List<RetrieveAuthorDTO> retrieveAuthorsByName(String name) {
        List<RetrieveAuthorDTO> retrieveAuthorDTOList = authorService.retrieveAuthorsByName(name);
        JPAUtil.closeEntityManager();
        return retrieveAuthorDTOList;
    }
}
