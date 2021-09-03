package com.mycomp.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mycomp.user.service.response.UserServiceResponse;

/**
 * 
 * @author Varsha T
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<Object> hanldeUserServiceException(UserServiceException ex, WebRequest webRequest) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(UserServiceResponse.builder().msg(ex.getMessage()).build());
	}

}
