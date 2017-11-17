package com.miskowskij.integration.api;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

import com.miskowskij.integration.IntegrationTestCase;

abstract public class SeleniumApiClient extends IntegrationTestCase {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SeleniumApiClient.class);

	private static Selenium selenium;

	protected String domain;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String driverPath = System.getProperty(PROPERTY_NAME_TEST_WEBDRIVER_CHROME_DRIVER);

		if (driverPath == null || driverPath.isEmpty()) {
			driverPath = System.getenv(PROPERTY_NAME_TEST_WEBDRIVER_CHROME_DRIVER.replace(".", "_"));
		}

		logger.debug("Property '" + PROPERTY_NAME_TEST_WEBDRIVER_CHROME_DRIVER + "': " + driverPath);

		if (driverPath == null || driverPath.isEmpty()) {
			throw new Exception("Property '" + PROPERTY_NAME_TEST_WEBDRIVER_CHROME_DRIVER + "' is not set.");
		}

		selenium = new Selenium();
		selenium.setDriverPath(driverPath);
		selenium.setup();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		selenium.close();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();

		this.domain = super.properties.get(PROPERTY_NAME_TEST_APP_DOMAIN);
	}

	protected void performGetRequest(String url) {
		logger.debug("Request url: " + url);
		selenium.getDriver().get(url);
	}

	protected String getResponseHtmlBody() {
		return selenium.getDriver().findElement(By.tagName("body")).getText();
	}

}
