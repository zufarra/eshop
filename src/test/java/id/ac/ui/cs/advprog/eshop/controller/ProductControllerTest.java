package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/createProduct")) // Perbaikan mapping
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/createProduct")) // Perbaikan mapping
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/listProduct"));
        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/listProduct")) // Perbaikan mapping
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testDeleteProductPost() throws Exception {
        mockMvc.perform(post("/product/deleteProduct/1")) // Perbaikan mapping
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/listProduct"));
        verify(productService, times(1)).delete("1");
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        when(productService.findById("1")).thenReturn(product);

        mockMvc.perform(get("/product/editProduct/1")) // Perbaikan mapping
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testEditProductPost() throws Exception {
        mockMvc.perform(post("/product/editProduct/1")) // Perbaikan mapping
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/listProduct"));
        verify(productService, times(1)).update(eq("1"), any(Product.class));
    }
}
