package com.progmatic.hazi3.dao;

import com.progmatic.hazi3.model.Author;
import com.progmatic.hazi3.model.Book;
import java.util.List;

public abstract class AuthorDAO implements DAO<Author>{
    public abstract List<Author> getAllByName(String name);

}
