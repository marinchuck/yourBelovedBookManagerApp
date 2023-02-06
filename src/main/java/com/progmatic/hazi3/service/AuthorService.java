package com.progmatic.hazi3.service;

import com.progmatic.hazi3.dao.AuthorDAO;
import com.progmatic.hazi3.dao.AuthorDAOImpl;
import com.progmatic.hazi3.dto.CreateAuthorDTO;
import com.progmatic.hazi3.dto.RetrieveAuthorDTO;
import com.progmatic.hazi3.dto.mapper.AuthorAndAuthorDTOMapper;
import com.progmatic.hazi3.exception.NotFoundResourceException;
import com.progmatic.hazi3.model.Author;
import java.util.List;
import java.util.Optional;

public class AuthorService {

    private final AuthorDAO dao;

    private AuthorService() {
        this.dao = AuthorDAOImpl.newInstance();
    }

    public static AuthorService newInstance() {
        return new AuthorService();
    }

    public void saveAuthor(CreateAuthorDTO createAuthorDTO) {
        dao.save(AuthorAndAuthorDTOMapper.INSTANCE.createAuthorDTOtoAuthor(createAuthorDTO));
    }

    public List<RetrieveAuthorDTO> readAllAuthors() {
        return dao.getAll().stream()
            .map(AuthorAndAuthorDTOMapper.INSTANCE::authorToRetrieveAuthorDTO)
            .toList();
    }

    public void updateAuthor(RetrieveAuthorDTO retrieveAuthorDTO, String[] params) {
        dao.update(AuthorAndAuthorDTOMapper.INSTANCE.retrieveAuthorDTOtoAuthor(retrieveAuthorDTO), params);
    }

    public Author getAuthorById(long id) throws NotFoundResourceException {
        Optional<Author> optionalAuthor = dao.get(id);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            throw new NotFoundResourceException("No such author found!");
        }
    }

    public RetrieveAuthorDTO retrieveAuthorById(long id) {
        try {
            return AuthorAndAuthorDTOMapper.INSTANCE.authorToRetrieveAuthorDTO(getAuthorById(id));
        } catch (NotFoundResourceException e) {
            System.out.println("\n" + e.getMessage());
            return null;
        }
    }

    public void deleteAuthorById(long id) {
        try {
            dao.delete(getAuthorById(id));
        } catch (NotFoundResourceException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public List<RetrieveAuthorDTO> retrieveAuthorsByName(String name) {
        return dao.getAllByName(name).stream()
            .map(AuthorAndAuthorDTOMapper.INSTANCE::authorToRetrieveAuthorDTO)
            .toList();
    }


}
