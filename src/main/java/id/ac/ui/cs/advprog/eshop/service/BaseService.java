package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.List;

public abstract class BaseService<T> implements GenericService<T> {
    protected BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.create(entity);
    }

    @Override
    public T update(String id, T updatedEntity) {
        return repository.update(id, updatedEntity);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public T findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        Iterator<T> iterator = repository.findAll();
        List<T> allEntities = new ArrayList<>();
        iterator.forEachRemaining(allEntities::add);
        return allEntities;
    }
}
