package com.miskowskij.app.integration.http.api.client;

import org.openqa.selenium.By;
import org.springframework.test.context.web.WebAppConfiguration;

import com.miskowskij.app.integration.http.api.SeleniumTestCase;

import org.junit.*;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;

@WebAppConfiguration
public class SimpleApiTest extends SeleniumTestCase {
	
	@Test
	public void testAccessRelativePathRoot() {
		driver.get(domain);
		assertEquals("welcome to root", driver.findElement(By.tagName("body")).getText());
	}
	
	@Test
	public void testAccessRelativePathFoo() {
		driver.get(domain + "/foo");
		assertEquals("welcome to foo", driver.findElement(By.tagName("body")).getText());
	}
	
	@Test
	public void testAccessRelativePathBar() {
		driver.get(domain + "/bar");
		assertEquals("welcome to bar", driver.findElement(By.tagName("body")).getText());
	}
	
	@Test
	public void testAddUserFoo() {
		driver.get(domain + "/user/foo");
		
		try {
			String responseData = driver.findElement(By.tagName("body")).getText();
			JSONObject jsonObject = new JSONObject(responseData);
			
			Long userId = new Long(jsonObject.getLong("id")); 
			assertNotNull(userId);
		} catch (JSONException e) {
			fail();
		}
	}
	
}