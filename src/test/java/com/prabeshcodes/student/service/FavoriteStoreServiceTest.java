package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.FavoriteStore;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.repository.FavoriteStoreRepository;
import com.prabeshcodes.student.repository.StoreRepository;
import com.prabeshcodes.student.repository.UserRepository;
import com.prabeshcodes.student.service.Impl.FavoriteStoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FavoriteStoreServiceTest {

    @Mock
    private FavoriteStoreRepository favoriteStoreRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private FavoriteStoreServiceImpl favoriteStoreService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testAddFavoriteStore() throws Exception {
//        // Mocking user and store
//        User user = new User();
//        user.setId(1L);
//
//        Store store = new Store();
//        store.setId(1L);
//
//        FavoriteStore favoriteStore = new FavoriteStore();
//        favoriteStore.setUser(user);
//        favoriteStore.setStore(store);
//        favoriteStore.setDateAdded(LocalDateTime.now());
//
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
//        when(favoriteStoreRepository.save(any())).thenReturn(favoriteStore); // Mocking the save method
//
//        favoriteStoreService.addFavoriteStore(favoriteStore);
//
//        verify(favoriteStoreRepository, times(1)).save(favoriteStore);
//        assertEquals(user.getFavoriteStores().size(), 1);
//        assertEquals(store.getFavoriteStores().size(), 1);
//    }


    @Test
    public void testGetFavoriteStoresByUserId() throws Exception {
        // Mocking user and favorite stores
        User user = new User();
        user.setId(1L);

        FavoriteStore favoriteStore1 = new FavoriteStore();
        favoriteStore1.setId(1L);
        favoriteStore1.setStore(new Store());
        favoriteStore1.getStore().setName("Store 1");

        FavoriteStore favoriteStore2 = new FavoriteStore();
        favoriteStore2.setId(2L);
        favoriteStore2.setStore(new Store());
        favoriteStore2.getStore().setName("Store 2");

        List<FavoriteStore> favoriteStores = new ArrayList<>();
        favoriteStores.add(favoriteStore1);
        favoriteStores.add(favoriteStore2);

        user.setFavoriteStores(favoriteStores);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        List<FavoriteStore> result = favoriteStoreService.getFavoriteStoresByUserId(1L);

        assertEquals(result.size(), 2);
    }
}
