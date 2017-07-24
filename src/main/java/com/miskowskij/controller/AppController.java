package com.miskowskij.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miskowskij.service.AppService;
  
@RestController
public class AppController {
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppController.class);

	private final static AppService app = new AppService();
	
    @RequestMapping("/")
    public String index() {
		logger.debug("[index] i am root.");
		
        return app.root();
    }
    
    @RequestMapping("/foo")
    public String foo() {
		logger.debug("[foo] i am foo.");
		
        return app.foo();
    }
    
    @RequestMapping("/bar")
    public String bar() {
		logger.debug("[bar] i am bar.");
		
        return app.bar();
    }
 
}