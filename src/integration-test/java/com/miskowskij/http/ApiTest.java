package com.miskowskij.http;

import org.openqa.selenium.By;

import com.miskowskij.IntegrationTest;

import org.junit.*;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class ApiTest extends Selenium {
	
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