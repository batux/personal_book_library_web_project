package com.personal.book.library.security.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;

import com.personal.book.library.security.AuthenticatedUser;
import com.personal.book.library.security.UserAuthenticationToken;

public class SMSAuthenticationValidator implements AuthenticationValidator {
	
	@Override
	public boolean validate(String value, Authentication authentication) {
		
		if(authentication instanceof UserAuthenticationToken) {
			
			UserAuthenticationToken userAuthenticationToken = (UserAuthenticationToken)authentication;
			
			if(userAuthenticationToken.getPrincipal() instanceof AuthenticatedUser) {
				
				// TODO: Fake SMS validation!
				if(StringUtils.isNotEmpty(value) && "123".equals(value)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
