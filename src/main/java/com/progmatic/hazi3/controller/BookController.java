package com.progmatic.hazi3.controller;

import com.progmatic.hazi3.dto.CreateBookDTO;
import com.progmatic.hazi3.dto.RetrieveAuthorDTO;
import com.progmatic.hazi3.dto.RetrieveBookDTO;
import com.progmatic.hazi3.service.BookService;
import com.progmatic.hazi3.util.JPAUtil;
import java.util.List;

public class BookController {

    private final BookService bookService;

   private BookController(){
       this.bookService = BookService.newInstance();
   }

   public static BookController newInstance(){
       return new BookController();
   }

    public void createBook(CreateBookDTO createBookDTO) {
        bookService.saveBook(createBookDTO);
        JPAUtil.closeEntityManager();
    }

    public List<RetrieveBookDTO> retrieveAllBooks() {
        List<RetrieveBookDTO> retrieveBookDTOList = bookService.retrieveAllBooks();
        JPAUtil.closeEntityManager();
        return retrieveBookDTOList;
    }


    public RetrieveBookDTO retrieveBookById(long id) {
        RetrieveBookDTO retrieveBookDTO = bookService.retrieveBookById(id);
        JPAUtil.closeEntityManager();
        return retrieveBookDTO;
    }

    public List<RetrieveBookDTO> retrieveBooksByTitleAndIsActive(String title, boolean isActive){
        List<RetrieveBookDTO> retrieveBookDTOList = bookService.retrieveBooksByTitleAndIsActive(title, isActive);
        JPAUtil.closeEntityManager();
        return retrieveBookDTOList;
    }

    public List<RetrieveBookDTO> retrieveBooksByAuthor(RetrieveAuthorDTO author){
        List<RetrieveBookDTO> retrieveBookDTOList = bookService.retrieveBooksByAuthor(author);
        JPAUtil.closeEntityManager();
        return retrieveBookDTOList;
    }

    public RetrieveBookDTO retrieveBookByIsbn(String isbn) {
        RetrieveBookDTO retrieveBookDTO = bookService.retrieveBookByIsbn(isbn);
        JPAUtil.closeEntityManager();
        return retrieveBookDTO;
    }

    public List<RetrieveBookDTO> retrieveBooksByAuthorAndIsActive(RetrieveAuthorDTO author, boolean isActive) {
        List<RetrieveBookDTO> retrieveBookDTOList = bookService.retrieveBooksByAuthorAndIsActive(author, isActive);
        JPAUtil.closeEntityManager();
        return retrieveBookDTOList;
    }

    public RetrieveBookDTO retrieveBookByIsbnAndIsActive(String isbn, boolean isActive) {
        RetrieveBookDTO retrieveBookDTO = bookService.retrieveBookByIsbnAndIsActive(isbn, isActive);
        JPAUtil.closeEntityManager();
        return retrieveBookDTO;
    }
}
