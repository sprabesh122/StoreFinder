package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByStoreId(Long storeId);

    Optional<Product> getProductById(Long productId);

    Product addProduct(Product product) throws Exception;
}