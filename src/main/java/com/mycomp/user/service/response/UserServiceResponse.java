package com.mycomp.user.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Varsha T
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserServiceResponse {
	String user;
	String msg;

}
