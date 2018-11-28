package com.personal.book.library.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.personal.book.library.datalayer.entity.UserRole;
import com.personal.book.library.security.UserAuthenticationToken;
import com.personal.book.library.security.UserRoleValue;
import com.personal.book.library.security.validator.SMSAuthenticationValidator;
import com.personal.book.library.security.validator.TOTPAuthenticationValidator;
import com.personal.book.library.util.Constant;

@WebFilter(urlPatterns = {"/validate/*"})
public class UserAuthenticationValidationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication instanceof UserAuthenticationToken) {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			
	  		UserAuthenticationToken userAuthenticationToken = (UserAuthenticationToken)authentication;
	  		
	  		UserRole authenticationCurrentStep = userAuthenticationToken.getAuthenticationCurrentStep();
	  		
	  		String authenticationKey = authenticationCurrentStep.getKey();
	  		
	  		UserRoleValue userRoleValue = UserRoleValue.AUTHENTICATION_COMPLETED;
	  		
	  		if(StringUtils.isNotEmpty(authenticationKey)) {
	  			
	  			boolean validationResult = false;
	  			
	  			userRoleValue = UserRoleValue.valueOf(authenticationKey);
	  			
	  			if(UserRoleValue.TOTP_AUTHENTICATION.equals(userRoleValue)) {
	  				
	  				String otpCode = req.getParameter("otp_code");
	  				TOTPAuthenticationValidator totpAuthenticationValidator = new TOTPAuthenticationValidator();
	  				validationResult = totpAuthenticationValidator.validate(otpCode, userAuthenticationToken);
	  			}
	  			else if(UserRoleValue.SMS_AUTHENTICATION.equals(userRoleValue)) {
	  				
	  				String smsCode = req.getParameter("sms_code");
	  				SMSAuthenticationValidator smsAuthenticationValidator = new SMSAuthenticationValidator();
	  				validationResult = smsAuthenticationValidator.validate(smsCode, userAuthenticationToken);
	  			}
	  			
	  			if(validationResult) {
	  				if(!CollectionUtils.isEmpty(userAuthenticationToken.getUserRoles())) {
	  					userAuthenticationToken.setAuthenticationCurrentStep(userAuthenticationToken.getUserRoles().get(0));
						userAuthenticationToken.getUserRoles().remove(0);
						userRoleValue = UserRoleValue.valueOf(userAuthenticationToken.getAuthenticationCurrentStep().getKey());
						
						UserAuthenticationToken newAuthentication = new UserAuthenticationToken(userAuthenticationToken.getPrincipal(), userAuthenticationToken.getCredentials(), userAuthenticationToken.getAuthorities());
						newAuthentication.setAuthenticationCurrentStep(userAuthenticationToken.getAuthenticationCurrentStep());
						newAuthentication.setUserRoles(userAuthenticationToken.getUserRoles());
						
						userAuthenticationToken = newAuthentication;
	  				}
	  				else {
	  					// All authentication steps are completed!
	  					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(authentication.getAuthorities());
	  			        authorities.add(new SimpleGrantedAuthority("AUTHENTICATED"));
	  			        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
	  			        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
	  			        userRoleValue = UserRoleValue.AUTHENTICATION_COMPLETED;
	  				}
	  				
	  			}
	  		}
	  		
	  		SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
	  		resp.setHeader(Constant.AUTHENTICATION_REDIRECT_HTTP_HEADER_PARAM, userRoleValue.toString());
	  		resp.setStatus(200);
	  	}
		
		//chain.doFilter(request, response);
	}
}
