package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Product edit(Product product, String productId){
        Product editedProduct = findProductById(productId);
        if (editedProduct != null) {
            editedProduct.setProductName(product.getProductName());
            editedProduct.setProductQuantity(product.getProductQuantity());
            return editedProduct;
        }
        return null;
    }

    public void delete(String productId) {
        Product deletedProduct = findProductById(productId);
        if (deletedProduct != null) {
            productData.remove(deletedProduct);
        }
    }

    public Product findProductById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
