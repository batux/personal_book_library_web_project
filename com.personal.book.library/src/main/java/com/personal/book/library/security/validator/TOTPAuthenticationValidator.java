package com.personal.book.library.security.validator;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;

import com.personal.book.library.security.AuthenticatedUser;
import com.personal.book.library.security.UserAuthenticationToken;

public class TOTPAuthenticationValidator implements AuthenticationValidator {
	
	private TOTPAuthenticator totpAuthenticator;

	public TOTPAuthenticationValidator() {
		totpAuthenticator = new TOTPAuthenticator();
	}
	
	@Override
	public boolean validate(String value, Authentication authentication) {
		
		if(authentication instanceof UserAuthenticationToken) {
			
			UserAuthenticationToken userAuthenticationToken = (UserAuthenticationToken)authentication;
			
			if(userAuthenticationToken.getPrincipal() instanceof AuthenticatedUser) {
				
				AuthenticatedUser authenticatedUser = (AuthenticatedUser)userAuthenticationToken.getPrincipal();
				
				String otpSecret = authenticatedUser.getOtpSecret();
				
				if(StringUtils.isNotEmpty(value)) {
					try {
						if(totpAuthenticator.verifyCode(otpSecret, Integer.valueOf(value), 2)) {
							return true;
						}
					}
					catch(InvalidKeyException | NoSuchAlgorithmException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		
		return false;
	}
}
