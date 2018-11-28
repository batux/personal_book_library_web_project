package com.personal.book.library.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.datalayer.repository.jpa.UserRepository;
import com.personal.book.library.security.captcha.ReCatpchaService;
import com.personal.book.library.util.HttpUtil;

@Service
public class SigninUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReCatpchaService reCatpchaService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) {

		String response = httpServletRequest.getParameter("g-recaptcha-response");
		
		String clientIp = HttpUtil.getClientIP(httpServletRequest);
		
		reCatpchaService.processRecaptchaResponse(response, clientIp);
		
		
		List<User> users = userRepository.findByUserName(userName, new PageRequest(0, 1));
		
		if(CollectionUtils.isEmpty(users)) {
			throw new BadCredentialsException("Unsuccessfull authentication!");
		}
		
		return new AuthenticatedUser(users.get(0));
	}
}
