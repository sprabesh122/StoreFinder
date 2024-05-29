package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.FavoriteStore;

import java.util.List;

public interface FavoriteStoreService {
    void addFavoriteStore(FavoriteStore favoriteStore) throws Exception;

    public List<FavoriteStore> getFavoriteStoresByUserId(Long userId) throws Exception;
}