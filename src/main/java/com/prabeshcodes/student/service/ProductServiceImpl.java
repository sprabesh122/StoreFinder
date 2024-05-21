package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Product;
import com.prabeshcodes.student.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByStoreId(Long storeId) {
        return productRepository.findAllByStoreId(storeId);
    }
}
