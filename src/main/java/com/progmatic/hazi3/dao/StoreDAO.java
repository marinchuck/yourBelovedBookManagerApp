package com.progmatic.hazi3.dao;

import com.progmatic.hazi3.model.Store;
import java.util.List;
import java.util.Optional;


public class StoreDAO implements DAO<Store> {

    private StoreDAO() {
    }

    public static StoreDAO newInstance() {
        return new StoreDAO();
    }

    @Override
    public Optional<Store> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Store> getAll() {
        return null;
    }

    @Override
    public void save(Store store) {

    }

    @Override
    public void update(Store store, String[] params) {

    }

    @Override
    public void delete(Store store) {

    }
}
