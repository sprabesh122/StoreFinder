package com.prabeshcodes.student.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prabeshcodes.student.model.Product;
import com.prabeshcodes.student.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @JsonIgnore
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @JsonIgnore
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @JsonIgnore
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) throws Exception {
        return productService.addProduct(product);
    }


    @JsonIgnore
    @GetMapping("/store/{store_id}")
    public List<Product> getByStoreId(@PathVariable Long storeId){
        return productService.getProductsByStoreId(storeId);
    }

}
