package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    User getUserByUsernameOrEmail(String identifier); // Add this method
    boolean isAdmin(User user);
}
