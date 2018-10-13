package com.personal.book.library.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class SigninFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {

	      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
	}
}
