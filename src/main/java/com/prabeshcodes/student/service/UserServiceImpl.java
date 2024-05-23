//package com.prabeshcodes.student.service;
//
//import com.prabeshcodes.student.model.User;
//import com.prabeshcodes.student.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    @Override
//    public User getUserByUsernameOrEmail(String identifier) {
//        User user = userRepository.findByUsername(identifier);
//        if (user == null) {
//            user = userRepository.findByEmail(identifier);
//        }
//        return user;
//    }
//
//    @Override
//    public boolean isAdmin(User user) {
//        return "admin".equalsIgnoreCase(user.getRole());
//    }
//}

package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Location;
import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.repository.LocationRepository;
import com.prabeshcodes.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public User saveUser(User user) {
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
    public User getUserByUsernameOrEmail(String identifier) {
        User user = userRepository.findByUsername(identifier);
        if (user == null) {
            user = userRepository.findByEmail(identifier);
        }
        return user;
    }

    @Override
    public boolean isAdmin(User user) {
        return "admin".equalsIgnoreCase(user.getRole());
    }

    @Override
    public User updateUserLocation(Long userId, Location location) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            locationRepository.save(location);
            user.setCurrentLocation(location);
            userRepository.save(user);
        }
        return user;
    }
}
