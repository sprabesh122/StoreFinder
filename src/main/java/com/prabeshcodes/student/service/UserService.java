package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.User;

public interface UserService {
    User getUserById(Long id);

    User getUserByUsername(String username);
}
