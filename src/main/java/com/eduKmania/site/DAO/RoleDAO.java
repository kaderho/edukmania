package com.eduKmania.site.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eduKmania.site.repository.RoleRepository;

@Transactional
@Repository
public class RoleDAO {
	 
	 @Autowired
	 private RoleRepository roleRepository;
	 
		public List<String> getRoleNames(Long userId) { 
			System.out.println("testrole");
	        return roleRepository.findByRoleName(userId);
	    }
}
