package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.service.StoreService;
import com.prabeshcodes.student.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@CrossOrigin
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping("/add")
//    public String add(@RequestBody Store store, @RequestHeader HttpHeaders headers) throws Exception {
//        Claims claims = jwtUtil.extractClaims(headers.getFirst("Authorization"));
//
//        if (claims.get("role").equals("user")) {
//            throw new Exception("User not Authorised");
//        }
//        storeService.saveStore(store);
//        return "New Store is Added";
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addStore(@RequestBody Store store) {
        storeService.saveStore(store);
        return ResponseEntity.ok("New Store is Added");
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store storeDetails, @RequestHeader HttpHeaders headers) throws Exception {
        Claims claims = jwtUtil.extractClaims(headers.getFirst("Authorization"));

        if (claims.get("role").equals("user")) {
            throw new Exception("User not Authorised");
        }

        return storeService.findById(id).map(store -> {
            store.setName(storeDetails.getName());
            store.setDescription(storeDetails.getDescription());
            store.setLocation(storeDetails.getLocation());
            // Update other fields as necessary
            return storeService.saveStore(store);
        }).orElseThrow(() -> new Exception("Store not found with id " + id));
    }

    @GetMapping("/getAll")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }
}
