//package com.prabeshcodes.student.controller;
//
//import com.prabeshcodes.student.model.User;
//import com.prabeshcodes.student.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:3000")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/add")
//    public String addUser(@RequestBody User user) {
//        user.setRole("user");
//        userService.saveUser(user);
//        return "New User Added";
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        User user = userService.getUserByUsernameOrEmail(loginRequest.getIdentifier());
//        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/username or password");
//        }
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//
//    @GetMapping("/username/{username}")
//    public User getUserByUsername(@PathVariable String username) {
//        return userService.getUserByUsername(username);
//    }
//
//    @GetMapping("/check-admin/{id}")
//    public String checkAdmin(@PathVariable Long id) {
//        User user = userService.getUserById(id);
//        if (user != null && userService.isAdmin(user)) {
//            return "User is an Admin";
//        } else {
//            return "User is not an Admin";
//        }
//    }
//
//    // Nested class for login request
//    public static class LoginRequest {
//        private String identifier;
//        private String password;
//
//        // Getters and setters
//        public String getIdentifier() {
//            return identifier;
//        }
//
//        public void setIdentifier(String identifier) {
//            this.identifier = identifier;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//    }
//}

package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.dtos.JwtResponse;
import com.prabeshcodes.student.model.Location;
import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.service.UserService;
import com.prabeshcodes.student.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        user.setRole("user");
        userService.saveUser(user);
        return "New User Added";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.getUserByUsernameOrEmail(loginRequest.getIdentifier());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            Location location = new Location();
            location.setLatitude(Double.parseDouble(loginRequest.getLatitude()));
            location.setLongitude(Double.parseDouble(loginRequest.getLongitude()));
            userService.updateUserLocation(user.getId(), location);

            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(token);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/username or password");
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/check-admin/{id}")
    public String checkAdmin(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null && userService.isAdmin(user)) {
            return "User is an Admin";
        } else {
            return "User is not an Admin";
        }
    }

    // Nested class for login request
    public static class LoginRequest {
        private String identifier;
        private String password;
        private String latitude;
        private String longitude;

        // Getters and setters
        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}

