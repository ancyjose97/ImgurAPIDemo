package com.imgur.demo.service.impl.test;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.imgur.demo.model.User;
import com.imgur.demo.repository.UserRepository;
import com.imgur.demo.service.impl.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(null);
        when(passwordEncoder.encode("testPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        
        User registeredUser = userService.registerUser(mockUser);

        // Assert
        assertNotNull(registeredUser);
        assertEquals("testUser", registeredUser.getUsername());
    }

    @Test
    void testAuthenticateUser_Success() {
      
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);
        when(passwordEncoder.matches("testPassword", "encodedPassword")).thenReturn(true);

        
        User authenticatedUser = userService.authenticateUser(mockUser);

        // Assert
        assertNotNull(authenticatedUser);
    }
    
    @Test
    void testRegisterUser_UserAlreadyExists() {
       
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

       
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(mockUser), "User already exists");
    }

    @Test
    void testAuthenticateUser_InvalidPassword() {
        
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("wrongPassword");

        User storedUser = new User();
        storedUser.setUsername("testUser");
        storedUser.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(storedUser);
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

       
        assertThrows(IllegalArgumentException.class, () -> userService.authenticateUser(mockUser), "Invalid credentials");
    }
}
