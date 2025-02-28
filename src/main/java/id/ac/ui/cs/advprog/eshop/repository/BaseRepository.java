package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Identifiable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class BaseRepository<T extends Identifiable> implements GenericRepository<T> {
    protected List<T> dataStore = new ArrayList<>();

    @Override
    public T create(T entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        dataStore.add(entity);
        return entity;
    }

    @Override
    public T update(String id, T updatedEntity) {
        for (int i = 0; i < dataStore.size(); i++) {
            if (dataStore.get(i).getId().equals(id)) {
                dataStore.set(i, updatedEntity);
                return updatedEntity;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        dataStore.removeIf(entity -> entity.getId().equals(id));
    }

    @Override
    public T findById(String id) {
        for (T entity : dataStore) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Iterator<T> findAll() {
        return dataStore.iterator();
    }
}
