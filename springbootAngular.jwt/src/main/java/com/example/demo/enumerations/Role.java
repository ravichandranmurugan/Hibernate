package com.example.demo.enumerations;

import com.example.demo.constant.AuthorityConstants;

public enum Role {
	ROLE_USER(AuthorityConstants.USER_AUTHORITIES),
	ROLE_HR(AuthorityConstants.HR_AUTHORITIES),
	ROLE_MANAGER(AuthorityConstants.MANAGER_AUTHORITIES),
	ROLE_ADMIN(AuthorityConstants.ADMIN_AUTHORITIES),
	ROLE_SUPER_ADMIN(AuthorityConstants.SUPER_ADMIN_AUTHORITIES);

	
	private String[] authorities;
	Role(String... userAuthorities) {
		// TODO Auto-generated constructor stub
		this.authorities=userAuthorities;
	}
	public String[] getAuthorities() {
		return authorities;
	}
	
	
}
