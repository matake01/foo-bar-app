package com.miskowskij.integration.api;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.miskowskij.integration.IntegrationTestCase;

abstract public class SeleniumApiClient extends IntegrationTestCase {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SeleniumApiClient.class);
	
	private static final String PROPERTY_NAME_WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String PROPERTY_NAME_APP_DOMAIN = "app.domain";

	private static Selenium selenium;
	
	@Autowired
	private Environment env;
	
	protected String domain;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String driverPath = System.getProperty(PROPERTY_NAME_WEBDRIVER_CHROME_DRIVER);
		logger.debug("WEBDRIVER => " + driverPath);

		if (driverPath == null) {
			throw new Exception("Property '" + PROPERTY_NAME_WEBDRIVER_CHROME_DRIVER + "' must be set.");
		}
		
		selenium = new Selenium();
		selenium.setup();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		selenium.close();
	}
	
	@Before
	public void setUp() {		
		domain = env.getRequiredProperty(PROPERTY_NAME_APP_DOMAIN);
	}
	
	protected void performGetRequest(String url) {
		logger.debug("Request url: " + url); 
		selenium.getDriver().get(url);
	}

	protected String getResponseHtmlBody() {
		return selenium.getDriver().findElement(By.tagName("body")).getText();
	}
	
}
