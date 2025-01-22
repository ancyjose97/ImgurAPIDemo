package com.imgur.demo.service;

import com.imgur.demo.model.User;

public interface UserService {

	 User registerUser(User user);
	
	 User authenticateUser(User user);
	 
	 
}
