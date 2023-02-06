package com.progmatic.hazi3.dao;

import static com.progmatic.hazi3.util.JPAUtil.getEntityManager;

import com.progmatic.hazi3.model.Author;
import com.progmatic.hazi3.model.Book;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;


public class BookDAOImpl extends BookDAO {
    public static BookDAOImpl newInstance() {
        return new BookDAOImpl();
    }


    @Override
    public Optional<Book> get(long id) {
        return Optional.ofNullable(getEntityManager().find(Book.class, id));

    }

    @Override
    public List<Book> getAll() {
        Query query = getEntityManager().createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    @Override
    public void save(Book book) {
        executeInsideTransaction(entityManager -> entityManager.persist(book));

    }

    @Override
    public void update(Book book, String[] params) {
        book.setTitle(params[0]);
        book.setIsbn(params[1]);
        book.setActive(Boolean.parseBoolean(params[2]));
        executeInsideTransaction(entityManager -> entityManager.merge(book));

    }

    @Override
    public void delete(Book book) {
        executeInsideTransaction(entityManager -> entityManager.remove(book));
    }

    public List<Book> getAllByTitle(String title){
        Query query = getEntityManager().createQuery("SELECT b FROM Book b WHERE b.title LIKE '%"+ title +"%'");
        return query.getResultList();
    }

    @Override
    public List<Book> getAllByTitleAndIsActive(String title, boolean isActive) {
        Query query = getEntityManager().createQuery("SELECT b FROM Book b WHERE b.title LIKE '%"+ title +"%'" + " AND b.isActive = " + isActive);
        return query.getResultList();
    }

    public List<Book> getAllByAuthor(Author author){
        Query query = getEntityManager().createQuery("SELECT b FROM Book b WHERE b.author.id = " + author.getId());
        return query.getResultList();
    }

    public Optional<Book> getByIsbn(String isbn) {
        Query query = getEntityManager().createQuery("SELECT b FROM Book b WHERE b.isbn LIKE '" + isbn + "'");
        return (Optional<Book>) query.getResultList().stream().findFirst();
    }

    @Override
    public List<Book> getAllByAuthorAndIsActive(Author author, boolean isActive) {
        Query query = getEntityManager().createQuery("SELECT b FROM Book b WHERE b.author.id = " + author.getId() + " AND b.isActive = " + isActive);
        return query.getResultList();
    }

    @Override
    public Optional<Book> getByIsbnAndIsActive(String isbn, boolean isActive) {
        Query query = getEntityManager().createQuery("SELECT b FROM Book b WHERE b.isbn LIKE '" + isbn + "'" + " AND b.isActive = " + isActive);
        return (Optional<Book>) query.getResultList().stream().findFirst();
    }
}
