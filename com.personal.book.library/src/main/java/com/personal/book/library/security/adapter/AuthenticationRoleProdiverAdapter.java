package com.personal.book.library.security.adapter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.personal.book.library.datalayer.entity.UserRole;
import com.personal.book.library.datalayer.entity.UserRoleList;

@Component
public class AuthenticationRoleProdiverAdapter {

	public Set<UserRole> convertToDaoAuthenticationProviders(List<UserRoleList> userRolesList) {
		
		Set<UserRole> userRoles = new LinkedHashSet<>();
		
		for(UserRoleList userRole : userRolesList) {
			
			if(userRole == null) continue;
			
			UserRole role = userRole.getUserRole();
			
			userRoles.add(role);
		}
		
		return userRoles;
	}
	
}
