package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Product;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.repository.ProductRepository;
import com.prabeshcodes.student.repository.StoreRepository;
import com.prabeshcodes.student.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Mocking product data
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(result.size(), 2);
    }

    @Test
    public void testGetProductsByStoreId() {
        // Mocking product data
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productRepository.findAllByStoreId(1L)).thenReturn(products);

        List<Product> result = productService.getProductsByStoreId(1L);

        assertEquals(result.size(), 2);
    }

    @Test
    public void testGetProductById() {
        // Mocking product data
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertEquals(result.isPresent(), true);
        assertEquals(result.get().getId(), 1L);
    }

//    @Test
//    public void testAddProduct() throws Exception {
//        // Mocking store data
//        Store store = new Store();
//        store.setId(1L);
//
//        // Mocking product data
//        Product product = new Product();
//        product.setId(1L);
//        product.setStore(store);
//
//        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
//        when(productRepository.save(product)).thenReturn(product);
//
//        Product result = productService.addProduct(product);
//
//        assertEquals(result.getId(), 1L);
//    }
}
