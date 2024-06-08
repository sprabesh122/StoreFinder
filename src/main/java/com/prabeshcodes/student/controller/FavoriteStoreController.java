package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.dtos.FavoriteStoreResponse;
import com.prabeshcodes.student.model.FavoriteStore;
import com.prabeshcodes.student.service.FavoriteStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/favorite-stores")
@CrossOrigin
public class FavoriteStoreController {
    @Autowired
    private FavoriteStoreService favoriteStoreService;

    // Logic to add favorite store
    @PostMapping("/add")
    public String addFavoriteStore(@RequestBody FavoriteStore favoriteStore) throws Exception {
        favoriteStoreService.addFavoriteStore(favoriteStore);
        return "New Favorite Store is Added";
    }

    @GetMapping("/user/{userId}")
    public List<FavoriteStoreResponse> getFavoriteStoresByUserId(@PathVariable Long userId) throws Exception {
        List<FavoriteStoreResponse> result = new ArrayList<>();
        List<FavoriteStore> favoriteStores = favoriteStoreService.getFavoriteStoresByUserId(userId);
        for(FavoriteStore favoriteStore : favoriteStores){
            FavoriteStoreResponse favoriteStoreResponse = new FavoriteStoreResponse();
            favoriteStoreResponse.setContactDetails(favoriteStore.getStore().getContactDetails());
            favoriteStoreResponse.setName(favoriteStore.getStore().getName());
            result.add(favoriteStoreResponse);
        }
        return result;
    }
}