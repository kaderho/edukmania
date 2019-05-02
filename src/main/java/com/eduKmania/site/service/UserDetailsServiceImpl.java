package com.eduKmania.site.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduKmania.site.model.appuser.Users;
import com.eduKmania.site.repository.RoleRepository;
import com.eduKmania.site.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository UserRepository;
	
	@Autowired
	RoleRepository RoleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		 
	Users users = UserRepository.findByUserName(username);
		 
		 if(users == null) {			 
			 System.out.println("test");
			 throw new UsernameNotFoundException(username);		 		
		}
		 
		 boolean enabled = true;
	     boolean accountNonExpired = true;
	     boolean credentialsNonExpired = true;
	     boolean accountNonLocked = true;
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		   List<String> roleNames = RoleRepository.findByRoleName(users.getUserId());
		  // [ROLE_USER, ROLE_ADMIN,..]
        
		for (String role : roleNames) {
			
			grantedAuthorities.add(new SimpleGrantedAuthority(role));
			System.out.println(role);
		}
		
		return new org.springframework
					.security
					.core
					.userdetails
					.User(users.getUserName(), 
							users.getPassword(), 
							enabled, accountNonExpired, 
					        credentialsNonExpired, 
					        accountNonLocked,
					        grantedAuthorities);
	}
}
