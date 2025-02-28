package id.ac.ui.cs.advprog.eshop.repository;

import java.util.List;
import java.util.Iterator;

public interface GenericRepository<T> {
    T create(T entity);
    T update(String id, T updatedEntity);
    void delete(String id);
    T findById(String id);
    Iterator<T> findAll();
}
