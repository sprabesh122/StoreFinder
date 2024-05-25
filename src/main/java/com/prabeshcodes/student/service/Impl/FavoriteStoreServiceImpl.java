package com.prabeshcodes.student.service.Impl;

import com.prabeshcodes.student.model.FavoriteStore;
import com.prabeshcodes.student.repository.FavoriteStoreRepository;
import com.prabeshcodes.student.service.FavoriteStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteStoreServiceImpl implements FavoriteStoreService {
    @Autowired
    private FavoriteStoreRepository favoriteStoreRepository;

    @Override
    public void addFavoriteStore(FavoriteStore favoriteStore) {
        favoriteStoreRepository.save(favoriteStore);
    }
}