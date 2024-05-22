package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        userService.saveUser(user);
        return "New User Added";
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
}
