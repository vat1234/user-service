package com.mycomp.user.service.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Varsha T
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
	String firstName;
	String lastName;
	String email;
	long mobileNumber;
	String gender;
	Date birthdate;
	String password;

}
