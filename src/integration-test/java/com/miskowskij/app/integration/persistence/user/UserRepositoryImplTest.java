package com.miskowskij.app.integration.persistence.user;

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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.miskowskij.app.integration.persistence.PersistenceTestCase;
import com.miskowskij.app.persistence.user.User;
import com.miskowskij.app.persistence.user.UserRepository;

/**
 * Integration test showing the basic usage of {@link UserRepository}.
 * 
 * @author Oliver Gierke
 * @author Thomas Darimont
 */
@Transactional
public class UserRepositoryImplTest extends PersistenceTestCase {

	@Autowired UserRepository repository;
	    
	/**
	 * Test invocation of custom method.
	 */
	@Test
	public void testCustomMethod() {

		User user = new User();
		user.setUsername("username");

		user = repository.save(user);

		List<User> users = repository.myCustomBatchOperation();

		assertNotNull(users);
		assertTrue(users.contains(user));
	}
	
}