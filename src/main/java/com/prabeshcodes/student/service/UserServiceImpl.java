package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user){
        // Additional logic for user roles can be added here if needed
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isAdmin(User user) {
        return "admin".equalsIgnoreCase(user.getRole());
    }
}
