package com.personal.book.library.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.personal.book.library.servicelayer.model.UserSessionSummary;
import com.personal.book.library.util.Constant;

@Component
public class SigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	  @Override
	  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
			
		  	AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
		  
		    HttpSession session = request.getSession(true);
		    session.setAttribute(Constant.USER_ID, authenticatedUser.getUser().getId());
		    session.setAttribute(Constant.USER_SUMMARY, new UserSessionSummary(authenticatedUser.getUser().getName(), authenticatedUser.getUser().getSurname()));
		    
			response.setStatus(200);
			//response.sendRedirect("/com.mizrak.otomotiv.credit.application.web/admin/creditapplication_dashboard.html");
	  }
	
}
