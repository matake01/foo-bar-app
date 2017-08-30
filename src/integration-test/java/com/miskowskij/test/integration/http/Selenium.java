package com.miskowskij.test.integration.http;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {

	protected static WebDriver driver;
	
	protected final String domain = "http://localhost:9090/aws-java-app";
	
	@BeforeClass
	public static void setup() {
		
		File resourcesDirectory = new File("src/integration-test/resources");
		String driverPath = resourcesDirectory.getAbsolutePath();
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		driver = new ChromeDriver(); // Default driver
		
		// driver = new FirefoxDriver();
	}
	
	@AfterClass
	public static void cleanUp(){
		driver.quit();
	}
	
	
}
