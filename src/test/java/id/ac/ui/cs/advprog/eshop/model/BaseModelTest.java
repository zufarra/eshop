package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public abstract class BaseModelTest<T extends Identifiable> {
    protected T entity;

    @BeforeEach
    void setup() {
        entity = createEntity();
    }

    protected abstract T createEntity();

    protected abstract String getSampleId();

    @Test
    void testGetId() {
        entity.setId(getSampleId());
        assertEquals(getSampleId(), entity.getId());
    }
}
