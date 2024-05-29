package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Category;
import com.prabeshcodes.student.model.Product;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.service.EmailService;
import com.prabeshcodes.student.repository.CategoryRepository;
import com.prabeshcodes.student.service.CategoryService;
import com.prabeshcodes.student.service.StoreService;
import com.prabeshcodes.student.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/stores")
@CrossOrigin
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @PostMapping("/send-email")
     public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
     emailService.sendNewProductMail(to, subject, text);
return "Email sent successfully!";
 }


    @PostMapping("/{storeId}/categories/add")
    public ResponseEntity<String> addCategoryToStore(@PathVariable Long storeId, @RequestBody Category category) {
        Store store = storeService.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found with id " + storeId));
        Category category1 = categoryRepository.findById(category.getId()).orElseThrow(() -> new RuntimeException("Category not found with id " + category.getId()));
        store.getCategories().add(category1);
        category1.setStore(store);
        storeService.saveStore(store);
        return ResponseEntity.ok("Category added to Store");
    }

    @GetMapping("/{storeId}/categories")
    public ResponseEntity<Set<Category>> getCategoriesOfStore(@PathVariable Long storeId) {
        Store store = storeService.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found with id " + storeId));
        return ResponseEntity.ok(store.getCategories());
    }

    @GetMapping("/category/{categoryId}")
    public List<Store> getStoresByCategory(@PathVariable Long categoryId) {
        // Assuming you have a method in storeService to retrieve stores by category ID
        return storeService.getStoreByCategory(categoryId);
    }

    @GetMapping("/product/{productId}")
    public List<Store> getStoresByProduct(@PathVariable Long productId){
        return storeService.getStoresByProduct(productId);
    }
}
