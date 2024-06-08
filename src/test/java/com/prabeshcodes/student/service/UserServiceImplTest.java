package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Location;
import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.repository.UserRepository;
import com.prabeshcodes.student.repository.LocationRepository;
import com.prabeshcodes.student.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);
        assertEquals("testUser", savedUser.getUsername());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User fetchedUser = userService.getUserById(1L);
        assertEquals(1L, fetchedUser.getId());
    }

    @Test
    public void testIsAdmin() {
        User user = new User();
        user.setRole("admin");

        boolean isAdmin = userService.isAdmin(user);
        assertEquals(true, isAdmin);
    }

    @Test
    public void testUpdateUserLocation() {
        User user = new User();
        user.setId(1L);

        Location location = new Location();
        location.setLatitude(0.0);
        location.setLongitude(0.0);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(locationRepository.save(any(Location.class))).thenReturn(location);

        User updatedUser = userService.updateUserLocation(1L, location);
        assertEquals(location, updatedUser.getCurrentLocation());
    }
}
