package com.mycomp.user.service.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.user.service.data.model.UserDetails;

/**
 * 
 * @author Varsha T
 *
 */
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	
	 public UserDetails findByEmail(String name);
}
