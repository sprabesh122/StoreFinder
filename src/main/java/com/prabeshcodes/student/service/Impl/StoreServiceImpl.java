package com.prabeshcodes.student.service.Impl;

import com.prabeshcodes.student.model.Category;
import com.prabeshcodes.student.model.Product;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.repository.StoreRepository;
import com.prabeshcodes.student.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> findById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> getStoreByCategory(Long category) {
        List<Store> stores = storeRepository.findAll();
        List<Store> resultStores = new ArrayList<>();
        for(Store store : stores){
            for(Category category1 : store.getCategories()){
                if(Objects.equals(category1.getId(), category)){
                    resultStores.add(store);
                    break;
                }
            }
        }
        if(resultStores.isEmpty()){
            logger.debug("No stores in this category");
        }
        return resultStores;
    }

    @Override
    public List<Store> getStoresByProduct(Long product) {
        List<Store> stores = storeRepository.findAll();
        List<Store> resultStores = new ArrayList<>();
        for(Store store : stores){
            for(Product product1 : store.getProducts()){
                if(Objects.equals(product1.getId(), product)){
                    resultStores.add(store);
                    break;
                }
            }
        }
        return resultStores;
    }

}