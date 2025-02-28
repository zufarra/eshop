package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/createProduct")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/createProduct")
    public String createProductPost(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:/product/listProduct";
    }

    @GetMapping("/listProduct")
    public String productListPage(Model model) {
        List<Product> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/editProduct/{productId}")
    public String editProductPage(@PathVariable("productId") String productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/editProduct/{productId}")
    public String editProductPost(@ModelAttribute Product product, @PathVariable("productId") String productId) {
        productService.update(productId, product);
        return "redirect:/product/listProduct";
    }

    @PostMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable("productId") String productId) {
        productService.delete(productId);
        return "redirect:/product/listProduct";
    }
}
