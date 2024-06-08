package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.dtos.StoreResponse;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.service.StoreService;
import com.prabeshcodes.student.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StoreControllerTest {

    @InjectMocks
    private StoreController storeController;

    @Mock
    private StoreService storeService;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStore() {
        Store store = new Store();
        store.setName("Test Store");

        when(storeService.saveStore(any(Store.class))).thenReturn(store);

        ResponseEntity<String> response = storeController.addStore(store);

        assertEquals("New Store is Added", response.getBody());
        verify(storeService, times(1)).saveStore(store);
    }

//    @Test
//    void testFindById() throws Exception {
//        Store store = new Store();
//        store.setId(1L);
//        store.setName("Test Store");
//        store.setDescription("Test Description");
//
//        when(storeService.findById(1L)).thenReturn(Optional.of(store));
//
//        StoreResponse response = storeController.findById(1L);
//
//        assertNotNull(response);
//        assertEquals("Test Store", response.getName());
//        assertEquals("Test Description", response.getDescription());
//    }

//    @Test
//    void testFindByIdNotFound() {
//        when(storeService.findById(1L)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(Exception.class, () -> storeController.findById(1L));
//        assertEquals("Store Not Found", exception.getMessage());
//    }

    @Test
    void testGetAllStores() {
        Store store1 = new Store();
        store1.setId(1L);
        store1.setName("Store 1");

        Store store2 = new Store();
        store2.setId(2L);
        store2.setName("Store 2");

        List<Store> stores = Arrays.asList(store1, store2);

        when(storeService.getAllStores()).thenReturn(stores);

        List<StoreResponse> response = storeController.getAllStores();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Store 1", response.get(0).getName());
        assertEquals("Store 2", response.get(1).getName());
    }

    @Test
    void testUpdateStore() throws Exception {
        Store store = new Store();
        store.setId(1L);
        store.setName("Updated Store");
        store.setDescription("Updated Description");

        Claims claims = mock(Claims.class);
        when(claims.get("role")).thenReturn("admin");
        when(jwtUtil.extractClaims(anyString())).thenReturn(claims);
        when(storeService.findById(1L)).thenReturn(Optional.of(store));
        when(storeService.saveStore(store)).thenReturn(store);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer token");

        Store updatedStoreDetails = new Store();
        updatedStoreDetails.setName("Updated Store");
        updatedStoreDetails.setDescription("Updated Description");

        Store updatedStore = storeController.updateStore(1L, updatedStoreDetails, headers);

        assertNotNull(updatedStore);
        assertEquals("Updated Store", updatedStore.getName());
        assertEquals("Updated Description", updatedStore.getDescription());
    }

    @Test
    void testUpdateStoreUnauthorized() {
        Claims claims = mock(Claims.class);
        when(claims.get("role")).thenReturn("user");
        when(jwtUtil.extractClaims(anyString())).thenReturn(claims);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer token");

        Store updatedStoreDetails = new Store();
        updatedStoreDetails.setName("Updated Store");
        updatedStoreDetails.setDescription("Updated Description");

        Exception exception = assertThrows(Exception.class, () -> storeController.updateStore(1L, updatedStoreDetails, headers));
        assertEquals("User not Authorised", exception.getMessage());
    }
}
