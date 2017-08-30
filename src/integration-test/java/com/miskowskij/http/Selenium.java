package com.miskowskij.http;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {

	protected static WebDriver driver;
	
	protected final String domain = "http://localhost:9090/aws-java-app";
	
	@BeforeClass
	public static void setup() {
		String driverPath = System.getenv("SELENIUM_WEBDRIVER_PATH");
		System.setProperty("webdriver.chrome.driver", driverPath);
				
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();
	}
	
	@AfterClass
	public static void cleanUp(){
		driver.quit();
	}
	
	
}
