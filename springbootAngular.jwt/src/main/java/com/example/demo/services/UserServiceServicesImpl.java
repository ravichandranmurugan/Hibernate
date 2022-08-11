package com.example.demo.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Modal.UserPrinciple;
import com.example.demo.Modal.UserService;
import com.example.demo.repository.UserServiceRepository;

@Service
@Transactional
@Qualifier("userServiceServicesImpl")
public class UserServiceServicesImpl implements UserServiceServices, UserDetailsService {
	private Logger LOGGER = LoggerFactory.getLogger(UserServiceServicesImpl.class);
	
	private UserServiceRepository userServiceRepository;
	
	@Autowired
	public UserServiceServicesImpl(UserServiceRepository userServiceRepository) {
		super();
		this.userServiceRepository = userServiceRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserService userService = userServiceRepository.findByFirstName(username);
		if(userService == null) {
			LOGGER.error("user not found "+username);
			throw new UsernameNotFoundException("user not found "+username);
		}
		else {
			LOGGER.info("user  found "+username);
			userService.setLastLoginDateDispaly(userService.getLastLoginDate());
			userService.setLastLoginDate(new Date());
			userServiceRepository.save(userService);
			UserPrinciple userPrinciple = new UserPrinciple(userService);
			LOGGER.info("Returning User By UserName"+ username);
			return userPrinciple;
		}
		
	}

}
