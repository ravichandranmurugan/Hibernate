package com.example.demo.services;

import java.util.List;

import com.example.demo.Exception.EmailExistingException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Exception.UsernameExistException;
import com.example.demo.Modal.UserService;

public interface UserServiceServices {

	UserService register(String firstname,String lastname,String username,String email) throws UserNotFoundException, UsernameExistException, EmailExistingException;
	
	List<UserService> getUsers();

	UserService findUserByUsername(String username);
	
	UserService findUserByEmail(String email);
	
}
