package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product edit(Product product, String productId);
    void delete(String productId);
    Product findProductById(String productId);
    public List<Product> findAll();
}

