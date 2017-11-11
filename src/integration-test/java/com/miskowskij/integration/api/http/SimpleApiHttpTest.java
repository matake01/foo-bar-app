package com.miskowskij.integration.api.http;

import org.springframework.test.context.web.WebAppConfiguration;

import com.miskowskij.integration.api.SeleniumApiClient;

import org.junit.*;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;

@WebAppConfiguration
public class SimpleApiHttpTest extends SeleniumApiClient {
	
	@Test
	public void testAccessRelativePathRoot() {
		performGetRequest(domain);
		String responseData = getResponseHtmlBody();
		assertEquals("welcome to root", responseData);
	}
	
	@Test
	public void testAccessRelativePathFoo() {
		performGetRequest(domain + "/foo");
		String responseData = getResponseHtmlBody();
		assertEquals("welcome to foo", responseData);
	}
	
	@Test
	public void testAccessRelativePathBar() {
		performGetRequest(domain + "/bar");
		String responseData = getResponseHtmlBody();
		assertEquals("welcome to bar", responseData);
	}
	
	@Test
	public void testAddUserFoo() {
		try {
			performGetRequest(domain + "/user/foo");
			String responseData = getResponseHtmlBody();
			JSONObject jsonObject = new JSONObject(responseData);
			
			Long userId = new Long(jsonObject.getLong("id")); 
			assertNotNull(userId);
		} catch (JSONException e) {
			fail();
		}
	}
	
}