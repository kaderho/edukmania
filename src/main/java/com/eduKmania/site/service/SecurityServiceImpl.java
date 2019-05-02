package com.eduKmania.site.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityServiceImpl implements SecurityService {

	
	@Override
	public String findLoggedInUsername() {
		
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		if (userDetails instanceof UserDetails) {
			
			return ((UserDetails)userDetails).getUsername();
		}
		
		return null;
	}
	
	

}
