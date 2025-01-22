package com.imgur.demo.service.impl;

import com.imgur.demo.model.User;
import com.imgur.demo.repository.UserRepository;
import com.imgur.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
    	
    	 logger.info("Registering user: {}", user.getUsername());
    	 
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User authenticateUser(User user) {
    	
    	logger.info("Authenticating user: {}", user.getUsername());
    	
    	 User userFromDb = userRepository.findByUsername(user.getUsername());

         if (userFromDb != null && user.getPassword().equals(userFromDb.getPassword())) {
            return user;
        }
        throw new IllegalArgumentException("Invalid credentials");
    }

}



