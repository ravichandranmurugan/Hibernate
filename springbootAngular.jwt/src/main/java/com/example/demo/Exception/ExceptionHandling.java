package com.example.demo.Exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.Objects;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.demoHttpResponse.HttpResponse;

@RestControllerAdvice
public class ExceptionHandling  {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static final String ACCOUNT_LOCKED = "your Account has been Locked.Please contact Administration";
	private static final String METHOD_IS_NOT_ALLOWED = "This Request is not Allowed on this Endpoint.please send a '%s' request";
	private static final String INTERNAL_SERVER_ERROR_MSG = "An error Occur while processing the Request";
	private static final String INCORRECT_CREDENTIALS = "Usernamae / Password incorrect.please try again";
	private static final String ACCOUNT_DISABLED = "Your Account has been disabled.If this is an Error, please contact Administration";
	private static final String ERROR_PROCESSING_FILE = "Error occured while Processing File";
	private static final String NOT_ENOUGH_PERMISSION = "You do not have Enough permission";

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<HttpResponse> accountDisabledException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<HttpResponse> badCredentialsException() {
		return createHttpResponse(HttpStatus.BAD_REQUEST, INCORRECT_CREDENTIALS);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HttpResponse> accessDeniedException() {
		return createHttpResponse(HttpStatus.FORBIDDEN, NOT_ENOUGH_PERMISSION);
	}

	@ExceptionHandler(LockedException.class)
	public ResponseEntity<HttpResponse> lockedException() {
		return createHttpResponse(HttpStatus.UNAUTHORIZED, ACCOUNT_LOCKED);
	}

	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException tokenExpiredException) {
		return createHttpResponse(HttpStatus.UNAUTHORIZED, tokenExpiredException.getMessage().toUpperCase());
	}

	@ExceptionHandler(EmailExistingException.class)
	public ResponseEntity<HttpResponse> emailExistException(EmailExistingException emailExistingException) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, emailExistingException.getMessage());
	}

	@ExceptionHandler(UsernameExistException.class)
	public ResponseEntity<HttpResponse> usernameExistException(UsernameExistException usernameExistException) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, usernameExistException.getMessage());
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException emailNotFoundException) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, emailNotFoundException.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException userNotFoundException) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, userNotFoundException.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException){
		HttpMethod supportedMethod = Objects.requireNonNull(httpRequestMethodNotSupportedException.getSupportedHttpMethods()).iterator().next();
		return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException noHandlerFoundException){
		return createHttpResponse(HttpStatus.BAD_REQUEST, "This Page Was Not found");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception){
		LOGGER.error(exception.getMessage());
		return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR,INTERNAL_SERVER_ERROR_MSG);
	}
	
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<HttpResponse> notFoundException(NoResultException noResultException){
		LOGGER.error(noResultException.getMessage());
		return createHttpResponse(HttpStatus.NOT_FOUND,noResultException.getMessage());
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<HttpResponse> ioException(IOException ioException){
		LOGGER.error(ioException.getMessage());
		return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR,ERROR_PROCESSING_FILE);
	}
	
	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		// TODO Auto-generated method stub
		return new ResponseEntity<HttpResponse>(new HttpResponse(new Date(), httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
	}
}
