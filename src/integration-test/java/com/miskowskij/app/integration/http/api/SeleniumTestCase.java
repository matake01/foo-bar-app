package com.miskowskij.app.integration.http.api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.miskowskij.app.integration.IntegrationTestCase;

public class SeleniumTestCase extends IntegrationTestCase {

	private static String PROPERTY_NAME_WEBDRIVER_PATH_ = "SELENIUM_WEBDRIVER_PATH";
	
	protected static WebDriver driver;
	
	protected final String domain = "http://localhost:9090/app";
	
	@BeforeClass
	public static void setup() {
		String driverPath = System.getenv(PROPERTY_NAME_WEBDRIVER_PATH_);
		
		if (driverPath == null) {
			driverPath = System.getProperty(PROPERTY_NAME_WEBDRIVER_PATH_);
		}
		
		initWebDriver(driverPath);
	}
	
	@AfterClass
	public static void cleanUp(){
		driver.quit();
	}
	
	private static void initWebDriver(String pathToWebDriver) {
		if (pathToWebDriver == null) {
			throw new RuntimeException("No path to Selenium WebDriver set.");
		}
		
		System.setProperty("webdriver.chrome.driver", pathToWebDriver);
		
		driver = new ChromeDriver(); // Default driver
		// driver = new FirefoxDriver();
	}
	
}
