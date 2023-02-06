package com.progmatic.hazi3.dao;

import static com.progmatic.hazi3.util.JPAUtil.getEntityManager;

import com.progmatic.hazi3.model.Author;
import com.progmatic.hazi3.model.Book;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

public class AuthorDAOImpl extends AuthorDAO {

    private AuthorDAOImpl() {
    }

    public static AuthorDAOImpl newInstance() {
        return new AuthorDAOImpl();
    }

    @Override
    public Optional<Author> get(long id) {
        return Optional.ofNullable(getEntityManager().find(Author.class, id));
    }

    @Override
    public List<Author> getAll() {
        Query query = getEntityManager().createQuery("SELECT a FROM Author a");
        return query.getResultList();
    }

    @Override
    public void save(Author author) {
        executeInsideTransaction(entityManager -> entityManager.persist(author));
    }

    @Override
    public void update(Author author, String[] params) {
        author.setName(params[0]);
        executeInsideTransaction(entityManager -> entityManager.merge(author));
    }

    @Override
    public void delete(Author author) {
        executeInsideTransaction(entityManager -> entityManager.remove(author));
    }

    @Override
    public List<Author> getAllByName(String name) {
            Query query = getEntityManager().createQuery("SELECT a FROM Author a WHERE a.name LIKE '%"+ name +"%'");
            return query.getResultList();
    }
}
