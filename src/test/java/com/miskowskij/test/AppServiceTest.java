package com.miskowskij.test;

import com.miskowskij.service.AppService;

import junit.framework.TestCase;

public class AppServiceTest extends TestCase {

	private AppService app;
	
	public void setUp() throws Exception {
		super.setUp();
		
		this.app = new AppService();
	}
	
	public void testIndex() {
		assertEquals(app.root(), "welcome to root");
	}
	
	public void testFoo() {
		assertEquals(app.foo(), "welcome to foo");
	}
	
	public void testBar() {
		assertEquals(app.bar(), "welcome to bar");
	}
	
}
