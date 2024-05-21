package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@CrossOrigin
public class StoreController {
    @Autowired
    private StoreService storeService;

    //code to save data into the database
    @PostMapping("/add")
    public String add(@RequestBody Store store){
        storeService.saveStore(store);
        return "New Store is Added";
    }

    //logic to get data
    @GetMapping("/getAll")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }
}
