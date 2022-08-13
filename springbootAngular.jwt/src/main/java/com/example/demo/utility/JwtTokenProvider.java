package com.example.demo.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.Modal.UserPrinciple;
import com.example.demo.constant.SecurityConstant;

@Component
public class JwtTokenProvider {

	@Value("jwt.secret")
	private String secret;

	public String generateJwtToken(UserPrinciple userPrinciple) {
		String[] claims = getClaimsFromUser(userPrinciple);
		return JWT.create().withIssuer(SecurityConstant.GET_ARRAY_LLC)
				.withAudience(SecurityConstant.GET_aRRAY_ADMINISTRATION).withIssuedAt(new Date())
				.withSubject(userPrinciple.getUsername()).withArrayClaim(SecurityConstant.AUTHORITIES, claims)
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(secret.getBytes()));
	}

	private String[] getClaimsFromUser(UserPrinciple principal) {
		List<String> authorties = new ArrayList<String>();
		for (GrantedAuthority grantedAuthority : principal.getAuthorities()) {
			authorties.add(grantedAuthority.getAuthority());
		}
		return authorties.toArray(new String[0]);
	}

	public List<GrantedAuthority> getAuthorities(String token) {
		String[] claims = getClaimsFromToken(token);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String claim : claims) {
			authorities.add(new SimpleGrantedAuthority(claim));
		}
		return authorities;
	}

	private String[] getClaimsFromToken(String token) {
		JWTVerifier verifier = getJwtVerifier();
		return verifier.verify(token).getClaim(SecurityConstant.AUTHORITIES).asArray(String.class);
	}

	private JWTVerifier getJwtVerifier() {
		JWTVerifier verifier;
		List<String> authorties = new ArrayList<String>();
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret.getBytes());
			verifier = JWT.require(algorithm).withIssuer(SecurityConstant.GET_ARRAY_LLC).build();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException(SecurityConstant.TOKEN_CANNOT_GE_vERIFIED);

		}
		return verifier;
	}

	public Authentication getAuthentication(String username, List<GrantedAuthority> authorities,
			HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				null, authorities);
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return authenticationToken;
	}

	public boolean isTokenValid(String username, String token) {
		JWTVerifier verifier = getJwtVerifier();
		return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
	}

	private boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date expiration = verifier.verify(token).getExpiresAt();
		return expiration.before(new Date());

	}

	public String getSubject(String token) {
		JWTVerifier verifier = getJwtVerifier();
		return verifier.verify(token).getSubject();
	}
}