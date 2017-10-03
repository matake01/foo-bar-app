package com.miskowskij.app.integration.persistence;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.miskowskij.app.integration.IntegrationTestCase;
import com.miskowskij.app.persistence.PersistenceInitializer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ PersistenceInitializer.class }  )
abstract public class PersistenceTestCase extends IntegrationTestCase {

}