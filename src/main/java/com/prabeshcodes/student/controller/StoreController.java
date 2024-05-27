package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.service.EmailService;
import com.prabeshcodes.student.service.StoreService;
import com.prabeshcodes.student.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@CrossOrigin
public class StoreController {
    @Autowired
    private StoreService storeService;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    //code to save data into the database

    @PostMapping("/add")
    public String add(@RequestBody Store store , @RequestHeader HttpHeaders headers) throws Exception {
        Claims claims = jwtUtil.extractClaims(headers.getFirst("Authorization"));

        if(claims.get("role") == "user"){
            throw new Exception("User not Authorised");
        }
        storeService.saveStore(store);
        return "New Store is Added";
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store storeDetails, @RequestHeader HttpHeaders headers) throws Exception {

        Claims claims = jwtUtil.extractClaims(headers.getFirst("Authorization"));

        if(claims.get("role") == "user"){
            throw new Exception("User not Authorised");
        }

        return (Store) storeService.findById(id).map(store -> {
            store.setName(storeDetails.getName());
            store.setDescription(storeDetails.getDescription());
            store.setLocation(storeDetails.getLocation());
            // Update other fields as necessary
            return storeService.saveStore(store);
        }).orElseThrow(() -> new Exception("Store not found with id " + id));
    }

    //logic to get data
    @GetMapping("/getAll")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @PostMapping("/send-email")
     public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
     emailService.sendNewProductMail(to, subject, text);
return "Email sent successfully!";
 }

}