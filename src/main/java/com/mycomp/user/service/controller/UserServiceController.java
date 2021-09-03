package com.mycomp.user.service.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.user.service.UserService;
import com.mycomp.user.service.model.User;
import com.mycomp.user.service.response.UserServiceResponse;

/**
 * 
 * @author Varsha T
 *
 */
@RestController
public class UserServiceController {

	@Resource(name = "userService")
	private UserService userService;
	@RequestMapping(value = "/v1/users", method = RequestMethod.POST)
	public ResponseEntity<UserServiceResponse> register(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user));

	}

}
