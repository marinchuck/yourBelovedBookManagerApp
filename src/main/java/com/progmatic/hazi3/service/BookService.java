package com.progmatic.hazi3.service;

import com.progmatic.hazi3.dao.BookDAO;
import com.progmatic.hazi3.dao.BookDAOImpl;
import com.progmatic.hazi3.dto.CreateBookDTO;
import com.progmatic.hazi3.dto.RetrieveAuthorDTO;
import com.progmatic.hazi3.dto.RetrieveBookDTO;
import com.progmatic.hazi3.dto.mapper.AuthorAndAuthorDTOMapper;
import com.progmatic.hazi3.dto.mapper.BookAndBookDTOMapper;
import com.progmatic.hazi3.exception.NotFoundResourceException;
import com.progmatic.hazi3.model.Book;
import java.util.List;
import java.util.Optional;

public class BookService {

    private final BookDAO dao;

    private BookService(){
        this.dao = BookDAOImpl.newInstance();
    }

    public static BookService newInstance() {
        return new BookService();
    }

    public void saveBook(CreateBookDTO createBookDTO){
        dao.save(BookAndBookDTOMapper.INSTANCE.createBookDTOtoBook(createBookDTO));
    }

    public List<RetrieveBookDTO> retrieveAllBooks() {
        return dao.getAll().stream()
            .map(BookAndBookDTOMapper.INSTANCE::bookToRetrieveBookDTO)
            .toList();
    }

    public RetrieveBookDTO retrieveBookById(long id) {
        try {
            return BookAndBookDTOMapper.INSTANCE.bookToRetrieveBookDTO(getBookById(id));
        } catch (NotFoundResourceException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Book getBookById(long id) throws NotFoundResourceException {
        Optional<Book> optionalBook = dao.get(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new NotFoundResourceException("No such book found!");
        }
    }

    public Book getByIsbn(String isbn) throws NotFoundResourceException {
        Optional<Book> optionalBook = dao.getByIsbn(isbn);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new NotFoundResourceException("No such book found!");
        }
    }

    public List<RetrieveBookDTO> retrieveBooksByTitleAndIsActive(String title, boolean isActive) {
        return dao.getAllByTitleAndIsActive(title, isActive).stream()
            .map(BookAndBookDTOMapper.INSTANCE::bookToRetrieveBookDTO)
            .toList();
    }



    public List<RetrieveBookDTO> retrieveBooksByAuthor(RetrieveAuthorDTO author) {
        return dao.getAllByAuthor(AuthorAndAuthorDTOMapper.INSTANCE.retrieveAuthorDTOtoAuthor(author)).stream()
            .map(BookAndBookDTOMapper.INSTANCE::bookToRetrieveBookDTO)
            .toList();
    }

    public RetrieveBookDTO retrieveBookByIsbn(String isbn) {
        try {
            return BookAndBookDTOMapper.INSTANCE.bookToRetrieveBookDTO(getByIsbn(isbn));
        } catch (NotFoundResourceException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<RetrieveBookDTO> retrieveBooksByAuthorAndIsActive(RetrieveAuthorDTO author, boolean isActive) {
        return dao.getAllByAuthorAndIsActive(AuthorAndAuthorDTOMapper.INSTANCE.retrieveAuthorDTOtoAuthor(author), isActive).stream()
            .map(BookAndBookDTOMapper.INSTANCE::bookToRetrieveBookDTO)
            .toList();
    }

    public RetrieveBookDTO retrieveBookByIsbnAndIsActive(String isbn, boolean isActive) {
        try {
            return BookAndBookDTOMapper.INSTANCE.bookToRetrieveBookDTO(getByIsbnAndIsActive(isbn, isActive));
        } catch (NotFoundResourceException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Book getByIsbnAndIsActive(String isbn, boolean isActive) throws NotFoundResourceException {
        Optional<Book> optionalBook = dao.getByIsbnAndIsActive(isbn, isActive);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new NotFoundResourceException("No such book found!");
        }
    }
}
