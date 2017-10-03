package com.miskowskij.app.unit.service;

import org.junit.Test;

import com.miskowskij.app.service.AppService;

import junit.framework.TestCase;

public class AppServiceTest extends TestCase {

	private AppService app;
	
	public void setUp() throws Exception {
		super.setUp();
		
		this.app = new AppService();
	}
	
	@Test
	public void testIndex() {
		assertEquals(app.root(), "welcome to root");
	}
	
	@Test
	public void testFoo() {
		assertEquals(app.foo(), "welcome to foo");
	}
	
	@Test
	public void testBar() {
		assertEquals(app.bar(), "welcome to bar");
	}
	
}
