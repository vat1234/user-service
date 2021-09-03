package com.mycomp.user.service.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.user.service.data.model.UserCredential;
/**
 * 
 * @author Varsha T
 *
 */
public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
	public UserCredential findByUsername(String name);
}
