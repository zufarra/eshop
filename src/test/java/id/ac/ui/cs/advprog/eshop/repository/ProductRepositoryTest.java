package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class ProductRepositoryTest extends BaseRepositoryTest<Product> {
    @Override
    protected BaseRepository<Product> createRepository() {
        return new ProductRepository();
    }

    @Override
    protected Product createEntity() {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        return product;
    }

    @Test
    void testUpdateProduct() {
        Product product = createEntity();
        product = repository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setId(product.getId()); // ID harus sama agar update berhasil
        updatedProduct.setProductName("Sampo Cap Bambang 2");
        updatedProduct.setProductQuantity(200);

        Product result = repository.update(product.getId(), updatedProduct);
        assertNotNull(result);
        assertEquals("Sampo Cap Bambang 2", result.getProductName());
        assertEquals(200, result.getProductQuantity());
    }

    @Test
    void testUpdateProductNotExist() {
        Product updatedProduct = new Product();
        updatedProduct.setId("non-existent-id");
        updatedProduct.setProductName("Sampo Cap Bango");
        updatedProduct.setProductQuantity(500);

        Product result = repository.update("non-existent-id", updatedProduct);
        assertNull(result);
    }
}
