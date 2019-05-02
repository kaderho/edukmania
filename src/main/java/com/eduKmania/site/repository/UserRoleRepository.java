package com.eduKmania.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduKmania.site.model.appuser.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
