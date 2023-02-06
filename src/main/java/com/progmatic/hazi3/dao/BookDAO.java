package com.progmatic.hazi3.dao;

import com.progmatic.hazi3.model.Author;
import com.progmatic.hazi3.model.Book;
import java.util.List;
import java.util.Optional;

public abstract class BookDAO implements DAO<Book> {

public abstract List<Book> getAllByTitle(String title);
    public abstract List<Book> getAllByTitleAndIsActive(String title, boolean isActive);


    public abstract List<Book> getAllByAuthor(Author author);
    public abstract Optional<Book> getByIsbn(String isbn);
    public abstract List<Book> getAllByAuthorAndIsActive(Author author, boolean isActive);

    public abstract Optional<Book> getByIsbnAndIsActive(String isbn, boolean isActive);
}
