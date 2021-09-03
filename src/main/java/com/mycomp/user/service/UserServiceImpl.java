package com.mycomp.user.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycomp.user.service.data.model.UserCredential;
import com.mycomp.user.service.data.model.UserDetails;
import com.mycomp.user.service.data.repository.UserCredentialRepository;
import com.mycomp.user.service.data.repository.UserDetailsRepository;
import com.mycomp.user.service.exception.UserServiceException;
import com.mycomp.user.service.model.User;
import com.mycomp.user.service.response.UserServiceResponse;

/**
 * 
 * @author Varsha T
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@Autowired
	public UserServiceImpl() {

	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserServiceResponse register(User user) throws UserServiceException {
		try {

			UserDetails userDetails = userDetailsRepository.save(UserDetails.builder().birthdate(user.getBirthdate())
					.email(user.getEmail()).firstName(user.getFirstName()).lastName(user.getLastName())
					.gender(user.getGender()).mobileNo(user.getMobileNumber()).build());
			if (null != userDetails) {
				byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
				String value = new String(decodedBytes);
				String cred = passwordEncoder.encode(value);
				userCredentialRepository
						.save(UserCredential.builder().username(userDetails.getEmail()).password(cred).build());
				return UserServiceResponse.builder().user(userDetails.getEmail()).msg("Registered successfully")
						.build();
			}

		} catch (Exception ecException) {
			throw new UserServiceException("Unable to register user " + user.getEmail(), ecException);
		}
		return UserServiceResponse.builder().user(user.getEmail()).msg("Resisteration unsuccessful").build();

	}

}
