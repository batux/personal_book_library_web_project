package com.personal.book.library.security.providers;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import com.personal.book.library.security.UserAuthenticationToken;

public class UserAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Override
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
		
		UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(principal, user.getPassword(), user.getAuthorities());
		return userAuthenticationToken;
	}
}
