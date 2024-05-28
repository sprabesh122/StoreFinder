package com.prabeshcodes.student.service.Impl;

import com.prabeshcodes.student.model.FavoriteStore;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.repository.FavoriteStoreRepository;
import com.prabeshcodes.student.repository.StoreRepository;
import com.prabeshcodes.student.repository.UserRepository;
import com.prabeshcodes.student.service.FavoriteStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteStoreServiceImpl implements FavoriteStoreService {
    @Autowired
    private FavoriteStoreRepository favoriteStoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    public void addFavoriteStore(FavoriteStore favoriteStore) throws Exception{
        User user = userRepository.findById(favoriteStore.getUser().getId())
                .orElseThrow(() -> new Exception("User not found with id " + favoriteStore.getUser().getId()));

        Store store = storeRepository.findById(favoriteStore.getStore().getId())
                .orElseThrow(() -> new Exception("Store not found with id " + favoriteStore.getStore().getId()));

        favoriteStore.setUser(user);
        favoriteStore.setStore(store);
        favoriteStore.setDateAdded(LocalDateTime.now());

        user.getFavoriteStores().add(favoriteStore);
        store.getFavoriteStores().add(favoriteStore);

        favoriteStoreRepository.save(favoriteStore);
    }
    @Override
    public List<FavoriteStore> getFavoriteStoresByUserId(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id " +userId));

        List<FavoriteStore> favoriteStores = user.getFavoriteStores();
        Logger logger = LoggerFactory.getLogger(getClass());
        for(FavoriteStore favs : favoriteStores){
            String storeName = favs.getStore().getName();
            logger.info("Store name: {}", storeName);
        }
        return user.getFavoriteStores();

    }
}