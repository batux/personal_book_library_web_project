package com.personal.book.library.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.util.Constant;


public class AuthenticatedUser implements UserDetails{

	private static final long serialVersionUID = 7586065682115955179L;
	
	private User user;
	
	public AuthenticatedUser(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		
		grantedAuthorities.add(new SimpleGrantedAuthority(Constant.USER_ROLE));
		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getNickName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return this.user;
	}
	
	public String getOtpSecret() {
		
		return (this.user == null) ? StringUtils.EMPTY : this.user.getOtpSecret();
	}

}
