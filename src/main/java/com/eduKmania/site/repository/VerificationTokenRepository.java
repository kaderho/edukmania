package com.eduKmania.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduKmania.site.model.appuser.Users;
import com.eduKmania.site.model.appuser.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	 List<VerificationToken> findByToken(String token);
	 List<VerificationToken> findByUsers(Users users);
}
