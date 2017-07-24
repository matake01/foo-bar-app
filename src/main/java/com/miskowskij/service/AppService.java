package com.miskowskij.service;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
  
@RestController
public class AppService {
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppService.class);

	public AppService() {
		
	}
	
    public String root() {		
        return "welcome to root";
    }
    
    public String foo() {
        return "welcome to foo";
    }
    
    public String bar() {
        return "welcome to bar";
    }
 
}