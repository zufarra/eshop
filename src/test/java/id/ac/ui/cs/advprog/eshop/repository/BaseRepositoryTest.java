package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public abstract class BaseRepositoryTest<T extends Identifiable> {
    protected BaseRepository<T> repository;
    protected abstract T createEntity();  // Method ini akan dibuat oleh subclass untuk setiap model

    @BeforeEach
    void setUp() {
        repository = createRepository();  // Set repository di setiap test turunannya
    }

    protected abstract BaseRepository<T> createRepository(); // Method ini diimplementasikan di test spesifik

    @Test
    void testCreateAndFind() {
        T entity = createEntity();
        entity = repository.create(entity);

        T savedEntity = repository.findById(entity.getId());
        assertNotNull(savedEntity);
        assertEquals(entity.getId(), savedEntity.getId());
    }

    @Test
    void testDelete() {
        T entity = createEntity();
        entity = repository.create(entity);

        repository.delete(entity.getId());
        T deletedEntity = repository.findById(entity.getId());
        assertNull(deletedEntity);
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<T> iterator = repository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindAllWithMultipleEntities() {
        T entity1 = createEntity();
        T entity2 = createEntity();
        repository.create(entity1);
        repository.create(entity2);

        Iterator<T> iterator = repository.findAll();
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertFalse(iterator.hasNext());
    }
}
