package com.personal.book.library.security.validator;

import org.springframework.security.core.Authentication;

public interface AuthenticationValidator {

	public boolean validate(String value, Authentication authentication);
}
