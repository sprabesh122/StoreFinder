package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByStoreId(Long storeId);
}