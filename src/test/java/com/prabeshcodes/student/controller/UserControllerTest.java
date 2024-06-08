package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Location;
import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.service.UserService;
import com.prabeshcodes.student.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("testUser");

        String response = userController.addUser(user);
        assertEquals("New User Added", response);
    }

    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPass");
        user.setRole("user");

        when(userService.getUserByUsernameOrEmail(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(anyString(), anyString())).thenReturn("fake-jwt-token");

        UserController.LoginRequest loginRequest = new UserController.LoginRequest();
        loginRequest.setIdentifier("testUser");
        loginRequest.setPassword("testPass");
        loginRequest.setLatitude("0.0");
        loginRequest.setLongitude("0.0");

        Location location = new Location();
        location.setLatitude(Double.parseDouble(loginRequest.getLatitude()));
        location.setLongitude(Double.parseDouble(loginRequest.getLongitude()));

        when(userService.updateUserLocation(any(Long.class), any(Location.class))).thenReturn(user);

//        ResponseEntity<String> response = userController.login(loginRequest);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("fake-jwt-token", response.getBody());
    }

    @Test
    public void testLoginInvalidCredentials() {
        when(userService.getUserByUsernameOrEmail(anyString())).thenReturn(null);

        UserController.LoginRequest loginRequest = new UserController.LoginRequest();
        loginRequest.setIdentifier("invalidUser");
        loginRequest.setPassword("invalidPass");

//        ResponseEntity<String> response = userController.login(loginRequest);
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//        assertEquals("Invalid email/username or password", response.getBody());
    }

    @Test
    public void testLoginInvalidPassword() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPass");

        when(userService.getUserByUsernameOrEmail(anyString())).thenReturn(user);

        UserController.LoginRequest loginRequest = new UserController.LoginRequest();
        loginRequest.setIdentifier("testUser");
        loginRequest.setPassword("wrongPass");

//        ResponseEntity<String> response = userController.login(loginRequest);
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//        assertEquals("Invalid email/username or password", response.getBody());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);

        when(userService.getUserById(1L)).thenReturn(user);

        User response = userController.getUserById(1L);
        assertEquals(1L, response.getId());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userService.getUserById(1L)).thenReturn(null);

        User response = userController.getUserById(1L);
        assertNull(response);
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User();
        user.setUsername("testUser");

        when(userService.getUserByUsername("testUser")).thenReturn(user);

        User response = userController.getUserByUsername("testUser");
        assertEquals("testUser", response.getUsername());
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        when(userService.getUserByUsername("testUser")).thenReturn(null);

        User response = userController.getUserByUsername("testUser");
        assertNull(response);
    }

    @Test
    public void testCheckAdmin() {
        User user = new User();
        user.setId(1L);
        user.setRole("admin");

        when(userService.getUserById(1L)).thenReturn(user);
        when(userService.isAdmin(user)).thenReturn(true);

        String response = userController.checkAdmin(1L);
        assertEquals("User is an Admin", response);
    }

    @Test
    public void testCheckNotAdmin() {
        User user = new User();
        user.setId(1L);
        user.setRole("user");

        when(userService.getUserById(1L)).thenReturn(user);
        when(userService.isAdmin(user)).thenReturn(false);

        String response = userController.checkAdmin(1L);
        assertEquals("User is not an Admin", response);
    }

    @Test
    public void testCheckAdminUserNotFound() {
        when(userService.getUserById(1L)).thenReturn(null);

        String response = userController.checkAdmin(1L);
        assertEquals("User is not an Admin", response);
    }
}