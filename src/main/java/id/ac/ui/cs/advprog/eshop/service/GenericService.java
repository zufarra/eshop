package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface GenericService<T> {
    T create(T entity);
    T update(String id, T updatedEntity);
    void delete(String id);
    T findById(String id);
    List<T> findAll();
}
