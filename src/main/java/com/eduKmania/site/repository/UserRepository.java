package com.eduKmania.site.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.model.appuser.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	
	Users findByUserName(String userName);
	
	List<Users> findByDemandeApprenant(Optional<DemandeApprenant> demandeApprenant);
}
