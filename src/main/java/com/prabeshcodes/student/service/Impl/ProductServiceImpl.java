package com.prabeshcodes.student.service.Impl;

import com.prabeshcodes.student.model.Product;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.repository.ProductRepository;
import com.prabeshcodes.student.repository.StoreRepository;
import com.prabeshcodes.student.service.ProductService;
import com.prabeshcodes.student.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

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

    @Override
    public Optional<Product> getProductById(Long productId){
        return productRepository.findById(productId);
    }

    @Override
    public Product addProduct(Product product) throws Exception {
        // Find the store associated with the product
        Long storeId = product.getStore().getId();
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception("Store not found with id " + storeId));

        // Add the product to the store's product list
        store.getProducts().add(product);

        // Save the product with the updated store
        return productRepository.save(product);
    }
}