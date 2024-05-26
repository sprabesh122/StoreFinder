package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Store saveStore(Store store);

    List<Store> getAllStores();

    Optional<Store> findById(Long id);
}