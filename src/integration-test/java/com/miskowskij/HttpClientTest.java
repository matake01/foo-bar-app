package com.miskowskij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.*;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class HttpClientTest {

	private static WebDriver driver;
	
	private final String domain = "http://localhost:9090/aws-java-app";
	
	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/mathiasakerberg/Documents/development/misc/chrome-driver/chromedriver");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public static void cleanUp(){
		driver.quit();
	}
	
	@Test
	public void testIndex(){
		driver.get(domain);
		assertEquals("welcome to root", driver.findElement(By.tagName("body")).getText());
	}
	
	@Test
	public void testFoo(){
		driver.get(domain + "/foo");
		assertEquals("welcome to foo", driver.findElement(By.tagName("body")).getText());
	}
	
	@Test
	public void testBar(){
		driver.get(domain + "/bar");
		assertEquals("welcome to bar", driver.findElement(By.tagName("body")).getText());
	}
	
} 