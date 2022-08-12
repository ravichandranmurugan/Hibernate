package com.example.demo.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Exception.EmailExistingException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Exception.UsernameExistException;
import com.example.demo.Modal.UserPrinciple;
import com.example.demo.Modal.UserService;
import com.example.demo.constant.SecurityConstant;
import com.example.demo.constant.UserServiceImplConstants;
import com.example.demo.enumerations.Role;
import com.example.demo.repository.UserServiceRepository;

@Service
@Transactional
@Qualifier("userServiceServicesImpl")
public class UserServiceServicesImpl implements UserServiceServices, UserDetailsService {

	private Logger LOGGER = LoggerFactory.getLogger(UserServiceServicesImpl.class);

	@Autowired
	private UserServiceRepository userServiceRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceServicesImpl(UserServiceRepository userServiceRepository, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userServiceRepository = userServiceRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserService userService = userServiceRepository.findByUserName(username);
		if (userService == null) {
			LOGGER.error(UserServiceImplConstants.USER_NOT_FOUND_BY_USERNAME + username);
			throw new UsernameNotFoundException(UserServiceImplConstants.USER_NOT_FOUND_BY_USERNAME + username);
		} else {
			LOGGER.info(UserServiceImplConstants.USER_FOUND_BY_USERNAME + username);
			userService.setLastLoginDateDispaly(userService.getLastLoginDate());
			userService.setLastLoginDate(new Date());
			userServiceRepository.save(userService);
			UserPrinciple userPrinciple = new UserPrinciple(userService);
			LOGGER.info("Returning User By UserName" + username);
			return userPrinciple;
		}

	}

	@Override
	public UserService register(String firstname, String lastname, String username, String email)
			throws UserNotFoundException, UsernameExistException, EmailExistingException {

		validateNewUserandEmail(StringUtils.EMPTY, username, email);
		UserService service = new UserService();

		service.setUserId(generateUserId());
		String password = generatePassword();
		String encodedPassword = encodedPassword(password);
		service.setFirstName(firstname);
		service.setLastName(lastname);
		service.setUserName(username);
		service.setEmail(email);
		service.setJoinDate(new Date());
		service.setPassword(encodedPassword);
		service.setActive(true);
		service.setNotLocked(true);
		service.setRole(Role.ROLE_USER.name());
		service.setAuthorities(Role.ROLE_USER.getAuthorities()); 
		service.setImgUrl(getImageUrl());
		userServiceRepository.save(service);
		LOGGER.info("New User Password :" + password);
		System.out.println(password);
		return service;
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	private String getImageUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(UserServiceImplConstants.DEFAULT_USER_IMG_PATH)
				.toUriString();
	}

	private String encodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(8);
	}

	private UserService validateNewUserandEmail(String currentUsername, String newUsername, String newEmail)
			throws UserNotFoundException, UsernameExistException, EmailExistingException {
		UserService userByUsername = findUserByUsername(newUsername);
		UserService userByNewEmail = findUserByEmail(newEmail);
		if (StringUtils.isNotBlank(currentUsername)) {

			UserService currentUser = findUserByUsername(currentUsername);
			if (currentUser == null) {
				throw new UserNotFoundException(UserServiceImplConstants.USER_NOT_FOUND_BY_USERNAME + currentUsername);
			}
			if (userByUsername != null && !currentUser.getId().equals(userByUsername.getId())) {
				throw new UsernameExistException(UserServiceImplConstants.USER_NAME_ALREADY_EXIST + newUsername);
			}

			if (userByNewEmail != null && !currentUser.getEmail().equals(userByNewEmail.getEmail())) {
				throw new EmailExistingException(UserServiceImplConstants.EMAIL_ALREADY_EXIST + newEmail);
			}
			return currentUser;
		} else {

			if (userByUsername != null) {
				throw new UsernameExistException(UserServiceImplConstants.USER_NAME_ALREADY_EXIST + newUsername);
			}
			if (userByNewEmail != null) {
				throw new EmailExistingException(UserServiceImplConstants.EMAIL_ALREADY_EXIST + newEmail);
			}
			return null;
		}

	}

	@Override
	public List<UserService> getUsers() {
		return userServiceRepository.findAll();
	}

	@Override
	public UserService findUserByUsername(String username) {
		return userServiceRepository.findByUserName(username);
	}

	@Override
	public UserService findUserByEmail(String email) {
		return userServiceRepository.findByEmail(email);
	}

}
