package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.FavoriteStore;
import com.prabeshcodes.student.service.FavoriteStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite-stores")
@CrossOrigin
public class FavoriteStoreController {
    @Autowired
    private FavoriteStoreService favoriteStoreService;

    // Logic to add favorite store
    @PostMapping("/add")
    public String addFavoriteStore(@RequestBody FavoriteStore favoriteStore) {
        favoriteStoreService.addFavoriteStore(favoriteStore);
        return "New Favorite Store is Added";
    }
}