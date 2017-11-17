package com.miskowskij.integration;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.miskowskij.app.AppInitializer;

@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ AppInitializer.class }  )
@ActiveProfiles("dev")
abstract public class IntegrationTestCase {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(IntegrationTestCase.class);

    protected static final String PROPERTY_NAME_TEST_APP_DOMAIN = "test.app.domain";
    protected static final String PROPERTY_NAME_TEST_SUPERUSER_AGENTID = "test.user.agentid";
    protected static final String PROPERTY_NAME_TEST_WEBDRIVER_CHROME_DRIVER = "test.webdriver.chrome.driver";

    @Autowired
    protected Environment env;

    protected Map<String, String> properties;

    @Before
    public void setUp() throws Exception {
        this.properties = new HashMap<String, String>();

        this.properties.put(PROPERTY_NAME_TEST_APP_DOMAIN, env.getRequiredProperty(PROPERTY_NAME_TEST_APP_DOMAIN));
        this.properties.put(PROPERTY_NAME_TEST_SUPERUSER_AGENTID, env.getRequiredProperty(PROPERTY_NAME_TEST_SUPERUSER_AGENTID));
        this.properties.put(PROPERTY_NAME_TEST_WEBDRIVER_CHROME_DRIVER, env.getRequiredProperty(PROPERTY_NAME_TEST_WEBDRIVER_CHROME_DRIVER)); // Set by extending classes

        logger.debug("Test properties: " + this.properties.toString());
    }

    @After
    public void tearDown() throws Exception {

    }

}
