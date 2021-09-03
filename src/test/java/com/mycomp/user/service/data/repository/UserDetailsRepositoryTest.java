package com.mycomp.user.service.data.repository;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycomp.user.service.data.model.UserDetails;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDetailsRepositoryTest {
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	@Test
	public void saveUserDetails() throws ParseException {

		UserDetails userDetails = UserDetails.builder()
				.birthdate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-09-03")).email("test@testmail.com")
				.firstName("Varsha").lastName("T").mobileNo(9090909L).gender("female").build();
		UserDetails res = userDetailsRepository.save(userDetails);

		assertNotNull(res);

	}

}
