package com.personal.book.library.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.datalayer.repository.jpa.UserRepository;

@Service
public class SigninUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		List<User> users = userRepository.findByUserName(userName, new PageRequest(0, 1));
		
		if(CollectionUtils.isEmpty(users)) {
			throw new BadCredentialsException("Unsuccessfull authentication!");
		}
		
		return new AuthenticatedUser(users.get(0));
	}
}
