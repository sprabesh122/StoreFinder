package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.service.StoreService;
import com.prabeshcodes.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addStore(@RequestBody Store store) {
        User user = userService.getUserById(store.getUser().getId());
        if (user != null) {
            store.setUser(user);
            storeService.saveStore(store);
            return "New Store Added";
        } else {
            return "User not found";
        }
    }

    @GetMapping("/all")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }
}
