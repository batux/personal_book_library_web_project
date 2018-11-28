package com.personal.book.library.security.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.personal.book.library.datalayer.entity.UserRole;
import com.personal.book.library.datalayer.entity.UserRoleList;
import com.personal.book.library.security.AuthenticatedUser;
import com.personal.book.library.security.UserAuthenticationToken;
import com.personal.book.library.security.UserRoleValue;
import com.personal.book.library.security.adapter.AuthenticationRoleProdiverAdapter;
import com.personal.book.library.servicelayer.UserService;
import com.personal.book.library.servicelayer.model.UserSessionSummary;
import com.personal.book.library.util.Constant;

@Component
public class SigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationRoleProdiverAdapter authenticationRoleProdiverAdapter;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
			
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();

		HttpSession session = request.getSession(true);
		session.setAttribute(Constant.USER_ID, authenticatedUser.getUser().getId());
		session.setAttribute(Constant.USER_SUMMARY, new UserSessionSummary(authenticatedUser.getUser().getName(),
				authenticatedUser.getUser().getSurname()));


		UserRoleValue userRoleValue = UserRoleValue.AUTHENTICATION_COMPLETED;
		
		if (authentication instanceof UserAuthenticationToken) {

			UserAuthenticationToken userAuthenticationToken = (UserAuthenticationToken) authentication;

			if (authenticatedUser.getUser() != null) {

				List<UserRoleList> userRolesList = userService
						.prepareActiveRoleList(authenticatedUser.getUser().getId());

				if (!CollectionUtils.isEmpty(userRolesList)) {

					Set<UserRole> userRoles = authenticationRoleProdiverAdapter
							.convertToDaoAuthenticationProviders(userRolesList);
					userAuthenticationToken.setUserRoles(new ArrayList<>(userRoles));
					userAuthenticationToken.setAuthenticationCurrentStep(userRolesList.get(0).getUserRole());
					userAuthenticationToken.getUserRoles().remove(0);
				}
			}
			
			userRoleValue = userAuthenticationToken.getAuthenticationCurrentStep() == null ? UserRoleValue.AUTHENTICATION_COMPLETED : UserRoleValue.valueOf(userAuthenticationToken.getAuthenticationCurrentStep().getKey());
		}
		
		response.setHeader(Constant.AUTHENTICATION_REDIRECT_HTTP_HEADER_PARAM, userRoleValue.toString());
		response.setStatus(200);
	}
	
}
