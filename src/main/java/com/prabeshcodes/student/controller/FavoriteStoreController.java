package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.FavoriteStore;
import com.prabeshcodes.student.service.FavoriteStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<FavoriteStore> getFavoriteStoresByUserId(@PathVariable Long userId) throws Exception {
        return favoriteStoreService.getFavoriteStoresByUserId(userId);
    }
}