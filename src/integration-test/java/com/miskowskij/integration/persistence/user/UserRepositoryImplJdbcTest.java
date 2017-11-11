/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.miskowskij.integration.persistence.user;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.miskowskij.app.persistence.user.User;
import com.miskowskij.app.persistence.user.UserRepository;
import com.miskowskij.integration.IntegrationTestCase;

/**
 * Integration test showing the basic usage of {@link UserRepository}.
 * 
 * @author Oliver Gierke
 * @author Thomas Darimont
 */
@Transactional
@ActiveProfiles("jdbc")
public class UserRepositoryImplJdbcTest extends IntegrationTestCase {
	
	@Autowired UserRepository repository;
	
    @PersistenceContext private EntityManager em;
    
	/**
	 * Test invocation of custom method.
	 */
	@Test
	public void testCustomMethod() {

		User user = new User();
		user.setUsername("username");
		user.setFirstname("firstname");
		user.setLastname("lastname");
		
		user = repository.save(user);
				
		// Persist to be able to fetch with JdbcTemplate
		em.flush();
		
		List<User> users = repository.myCustomBatchOperation();

		assertNotNull(users);
		assertTrue(users.contains(user));
	}
	
}