package com.eduKmania.site.DAO;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eduKmania.site.model.appuser.Users;
import com.eduKmania.site.repository.UserRepository;

@Transactional
@Repository
public class UserDAO {

	    @Autowired
	    private UserRepository userRepository;
	 
	    public Users findUserAccount(String userName) {
	        try {
	           
	        	Users userFind = userRepository.findByUserName(userName);
	        	
	        	System.out.println("testuser");
	        	System.out.println(userFind.getUserName());
	        	
	            return userFind;
	        } catch (NoResultException e) {
	            return null;	        }
	  
	    }
}
