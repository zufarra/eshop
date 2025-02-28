package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductName("Product1");
        product.setProductQuantity(10);

        when(productRepository.create(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Product1", createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductName("UpdatedProduct");
        product.setProductQuantity(20);

        when(productRepository.update(anyString(), any(Product.class))).thenReturn(product);
        Product updatedProduct = productService.update("1", product);

        assertNotNull(updatedProduct);
        assertEquals("UpdatedProduct", updatedProduct.getProductName());
        verify(productRepository, times(1)).update("1", product);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete("1");
        productService.delete("1");
        verify(productRepository, times(1)).delete("1");
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductName("Product1");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductName("Product2");
        product2.setProductQuantity(20);

        Iterator<Product> iterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> products = productService.findAll();
        assertEquals(2, products.size());
        assertEquals("Product1", products.get(0).getProductName());
        assertEquals("Product2", products.get(1).getProductName());
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductName("Product1");
        product.setProductQuantity(10);

        when(productRepository.findById("1")).thenReturn(product);
        Product foundProduct = productService.findById("1");

        assertNotNull(foundProduct);
        assertEquals("Product1", foundProduct.getProductName());
    }
}
