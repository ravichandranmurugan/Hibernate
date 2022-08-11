package com.example.demo.constant;

public class SecurityConstant {

	public static final Long EXPIRATION_TIME=(long) 432_000_000;
	
	public static final String TOKEN_PREFIX = "Bearer";

	public static final String JWT_TOKEN_HEADER = "jwt-Token";
	
	public static final String TOKEN_CANNOT_GE_vERIFIED = "Token canot be Verified";
	
	public static final String GET_ARRAY_LLC = "Get Arrary LLC";
	
	public static final String GET_aRRAY_ADMINISTRATION="user Management portal";
	
	public static final String AUTHORITIES = "authorities";
	
	public static final String FORBIDDEN_MESSAGE = "you need to login to access this page";
	
	public static final String ACCESS_DENIED_MESSAGE = "you dont have the permission to access this page";
	
	public static final String OPTIONS_HTTP_METHOS= "OPTIONSD";
	
	public static final String[] PUBLIC_URLs= {"/user/login","/user/register","/user/resetpassword/**","/user/image/**"}; 
	
}
