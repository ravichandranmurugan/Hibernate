package com.example.demo.controller;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.EmailExistingException;
import com.example.demo.Exception.ExceptionHandling;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Exception.UsernameExistException;
import com.example.demo.Modal.UserPrinciple;
import com.example.demo.Modal.UserService;
import com.example.demo.constant.SecurityConstant;
import com.example.demo.services.UserServiceServices;
import com.example.demo.utility.JwtTokenProvider;

@RestController
@RequestMapping(path = { "/user", "/*" })
public class HomeController extends ExceptionHandling {
	@Autowired
	private UserServiceServices userServiceServices;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	
//	public HomeController(UserServiceServices userServiceServices, AuthenticationManager authenticationManager,
//			JwtTokenProvider jwtTokenProvider) {
//		super();
//		this.userServiceServices = userServiceServices;
//		this.authenticationManager = authenticationManager;
//		this.jwtTokenProvider = jwtTokenProvider;
//	}
	@PostMapping("/login")
	public ResponseEntity<UserService> login(@RequestBody UserService userService) {

		System.out.println("jjsdjdsj");
		authenticate(userService.getUserName(),userService.getPassword());
		UserService service = userServiceServices.findUserByUsername(userService.getUserName());
		UserPrinciple userPrincipal = new UserPrinciple(service);
		HttpHeaders jwtHeaders = getJwtHeader(userPrincipal);
		return new ResponseEntity<>(service,jwtHeaders,HttpStatus.OK);

	}
	@PostMapping("/register")
	public ResponseEntity<UserService> register(@RequestBody UserService userService)
			throws UserNotFoundException, UsernameExistException, EmailExistingException {
		System.out.println("dsaedfsdf");
		UserService newUser = userServiceServices.register(userService.getFirstName(), userService.getLastName(),
				userService.getUserName(), userService.getEmail());
		return new ResponseEntity<UserService>(newUser, HttpStatus.OK);

	}

	

	private HttpHeaders getJwtHeader(UserPrinciple userPrincipal) {
		HttpHeaders httpheaders= new HttpHeaders();
		httpheaders.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
		return httpheaders;
	}

	private void authenticate(String userName, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		
	}

}
