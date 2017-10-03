package com.miskowskij.app.service;

import org.slf4j.LoggerFactory;

public class AppService {
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppService.class);

	public AppService() {
		
	}
	
    public String root() {		
    	logger.debug("welcome to root!");
        return "welcome to root";
    }
    
    public String foo() {
    	logger.debug("welcome to foo!");
        return "welcome to foo";
    }
    
    public String bar() {
    	logger.debug("welcome to bar!");
        return "welcome to bar";
    }
 
}