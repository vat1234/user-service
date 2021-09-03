package com.mycomp.user.service;

import com.mycomp.user.service.exception.UserServiceException;
import com.mycomp.user.service.model.User;
import com.mycomp.user.service.response.UserServiceResponse;

/**
 * 
 * @author Varsha T
 *
 */
public interface UserService {
	/**
	 * 
	 * @param user
	 */
	UserServiceResponse register(User user) throws UserServiceException;
}
