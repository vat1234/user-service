package com.mycomp.user.service.data.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycomp.user.service.data.model.UserCredential;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserCredentialRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@Test
	public void findUserCredDetailsWhenUsernameisGiven() {
		UserCredential userCredential = new UserCredential("test@testmail.com", "encrtpppp######");
		entityManager.persist(userCredential);
		entityManager.flush();

		UserCredential res = userCredentialRepository.getById("test@testmail.com");
		assertEquals(res.getPassword(), userCredential.getPassword());
	}

	@Test
	public void saveUserCrential() {

		UserCredential userCredential = new UserCredential("test@testmail.com", "encrtpppp######");
		entityManager.persist(userCredential);
		entityManager.flush();

		UserCredential res = userCredentialRepository.getById("test@testmail.com");
		assertNotNull(res);
		

	}
}
