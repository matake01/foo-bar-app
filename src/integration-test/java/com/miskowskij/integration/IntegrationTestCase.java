package com.miskowskij.integration;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.miskowskij.app.persistence.PersistenceInitializer;

@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ PersistenceInitializer.class }  )
@ActiveProfiles("dev")
abstract public class IntegrationTestCase {

}
