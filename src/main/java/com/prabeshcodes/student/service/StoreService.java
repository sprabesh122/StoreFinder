package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Store;

import java.util.List;

public interface StoreService {
    Store saveStore(Store store);

    List<Store> getAllStores();
}
