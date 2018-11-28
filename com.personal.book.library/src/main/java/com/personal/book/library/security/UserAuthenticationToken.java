package com.personal.book.library.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.personal.book.library.datalayer.entity.UserRole;

public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = -7942978335314499495L;
	
	private UserRole authenticationCurrentStep;
	
	private List<UserRole> userRoles;

	public UserAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
		this.setUserRoles(new ArrayList<>());
	}
	
	public UserAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public UserRole getAuthenticationCurrentStep() {
		return authenticationCurrentStep;
	}

	public void setAuthenticationCurrentStep(UserRole authenticationNextStep) {
		this.authenticationCurrentStep = authenticationNextStep;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}
