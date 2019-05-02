package com.eduKmania.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduKmania.site.model.appuser.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT ur.role.roleName FROM UserRole ur WHERE ur.users.userId =:id")
	List<String> findByRoleName(Long id);
	
	Role findByRoleName(String roleName);

}
