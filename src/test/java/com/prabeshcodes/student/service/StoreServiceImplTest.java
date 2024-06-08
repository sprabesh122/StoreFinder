package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Category;
import com.prabeshcodes.student.model.Product;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.repository.StoreRepository;
import com.prabeshcodes.student.service.Impl.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreServiceImplTest {

    @InjectMocks
    private StoreServiceImpl storeService;

    @Mock
    private StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStore() {
        Store store = new Store();
        store.setName("Test Store");

        when(storeRepository.save(store)).thenReturn(store);

        Store savedStore = storeService.saveStore(store);

        assertNotNull(savedStore);
        assertEquals("Test Store", savedStore.getName());
    }

    @Test
    void testGetAllStores() {
        Store store1 = new Store();
        store1.setId(1L);
        store1.setName("Store 1");

        Store store2 = new Store();
        store2.setId(2L);
        store2.setName("Store 2");

        List<Store> stores = Arrays.asList(store1, store2);

        when(storeRepository.findAll()).thenReturn(stores);

        List<Store> allStores = storeService.getAllStores();

        assertNotNull(allStores);
        assertEquals(2, allStores.size());
        assertEquals("Store 1", allStores.get(0).getName());
    }

    @Test
    void testFindById() {
        Store store = new Store();
        store.setId(1L);
        store.setName("Test Store");

        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        Optional<Store> foundStore = storeService.findById(1L);

        assertTrue(foundStore.isPresent());
        assertEquals("Test Store", foundStore.get().getName());
    }

    @Test
    void testGetStoreByCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Category 1");

        Store store = new Store();
        store.setId(1L);
        store.setName("Test Store");
        store.setCategories(new HashSet<>(Collections.singletonList(category)));

        when(storeRepository.findAll()).thenReturn(Collections.singletonList(store));

        List<Store> stores = storeService.getStoreByCategory("Category 1");

        assertNotNull(stores);
        assertEquals(1, stores.size());
        assertEquals("Test Store", stores.get(0).getName());
    }

    @Test
    void testGetStoresByProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");

        Store store = new Store();
        store.setId(1L);
        store.setName("Test Store");
        store.setProducts(Collections.singletonList(product));

        when(storeRepository.findAll()).thenReturn(Collections.singletonList(store));

        List<Store> stores = storeService.getStoresByProduct("Product 1");

        assertNotNull(stores);
        assertEquals(1, stores.size());
        assertEquals("Test Store", stores.get(0).getName());
    }
}
